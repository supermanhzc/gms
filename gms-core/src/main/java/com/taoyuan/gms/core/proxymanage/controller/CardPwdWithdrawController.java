package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.CardPwdWithdrawApi;
import com.taoyuan.gms.core.adminmanage.controller.BaseController;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.core.proxymanage.service.ICardPwdWithdrawService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import com.taoyuan.gms.model.entity.proxy.CardPassword;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
public class CardPwdWithdrawController extends BaseController implements CardPwdWithdrawApi {

    @Autowired
    private ICardPwdWithdrawService service;

    @Autowired
    private ICardPasswordService cpService;

    @Autowired
    private TyUserService userService;

    @Override
    public TyResponse getLatest10() {
        return new TySuccessResponse(service.getLatest10());
    }

    @Override
    public TyResponse withdraw(List<CardPassword> cardPasswordList) {
        if (CollectionUtils.isEmpty(cardPasswordList)) {
            return new TySuccessResponse(null);
        }

        Date now = new Date();
        List<CardPasswordEntity> dbList = new ArrayList<CardPasswordEntity>();
        for (CardPassword cp : cardPasswordList) {
            String cardId = cp.getCardId();
            if (StringUtils.isEmpty(cardId)) {
                throw new ValidateException("待撤销卡密卡号不能为空。");
            }
            String pwd = cp.getPassword();
            if (StringUtils.isEmpty(pwd)) {
                throw new ValidateException("待撤销卡密密码不能为空。");
            }

            QueryWrapper<CardPasswordEntity> wrapper = new QueryWrapper<CardPasswordEntity>();
            wrapper.lambda().eq(CardPasswordEntity::getCardId, cardId).eq(CardPasswordEntity::getCardPassword, pwd);
            CardPasswordEntity dbValue = cpService.getOne(wrapper);
            if (null != dbValue) {
                //只有未充值的卡密才能被回收，已充值2和已注销3的卡密不能被回收
                if (dbValue.getStatus() == 1) {
                    dbValue.setStatus(2);
                    dbValue.setEndTime(now);
                    dbList.add(dbValue);
                }
            }
        }
        log.info("card pwd list is {}", dbList);

        if (!CollectionUtils.isEmpty(dbList)) {
            //回收后讲卡状态设置为已充值，同时记录卡密回收记录
            Map<String, CardPwdWithdrawEntity> map = new HashMap<String, CardPwdWithdrawEntity>();
            for (CardPasswordEntity card : dbList) {
                CardPasswordEntity dbValue = cpService.getByCardId(card.getCardId());
                log.info("Card info:{}", dbValue);
                CardPwdWithdrawEntity cpw = null;
                String rechargeId = dbValue.getRechargeId();
                log.info("卡面值：{}", dbValue.getMoney());
                if (map.containsKey(rechargeId)) {
                    cpw = map.get(rechargeId);
                    cpw.setCount(cpw.getCount() + 1);
                    cpw.setAmount(dbValue.getMoney().add(cpw.getAmount()));
                } else {
                    TyUser user = userService.getUserById(Long.valueOf(rechargeId));
                    cpw = new CardPwdWithdrawEntity();
                    cpw.setProxyId(TySession.getCurrentUser().getUserId());
                    cpw.setProxyName(TySession.getCurrentUser().getName());
                    cpw.setMemberId(user.getId());
                    cpw.setMemberName(user.getName());
                    cpw.setTime(now);
                    cpw.setCount(1);
                    cpw.setAmount(dbValue.getMoney());
                }
                map.put(rechargeId, cpw);
            }

            //保存卡密回收记录
            service.saveOrUpdateBatch(map.values());

            //TODO
            // 给用户充值，给用户加上充值额度

            //全部完成后更改卡状态为已回收
            cpService.saveOrUpdateBatch(dbList);
            return new TySuccessResponse(map.values());
        }

        return new TySuccessResponse(null);
    }
}
