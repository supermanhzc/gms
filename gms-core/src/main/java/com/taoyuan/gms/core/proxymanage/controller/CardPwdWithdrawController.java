package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
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

import java.math.BigDecimal;
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


        List<String> cardIdList = new ArrayList<String>();

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
                cardIdList.add(cardId);
            }
        }
        log.info("card id list is {}", cardIdList);
        CardPasswordEntity entity = new CardPasswordEntity();
        entity.setStatus(3);
        cpService.update(entity, new QueryWrapper<CardPasswordEntity>().in("card_id", cardIdList));

        Date now = new Date();
        Map<String, CardPwdWithdrawEntity> map = new HashMap<String, CardPwdWithdrawEntity>();
        for(String cardId:cardIdList){
            CardPasswordEntity dbValue = cpService.getCardPasswordById(cardId);
            List<CardPwdWithdrawEntity> list = new ArrayList<CardPwdWithdrawEntity>();
            CardPwdWithdrawEntity cpw = new CardPwdWithdrawEntity();
            String rechargeId = dbValue.getRechargeId();
            TyUser user = userService.getUserById(Long.valueOf(rechargeId));
            cpw.setMemberId(user.getId());
            cpw.setMemberName(user.getName());
            cpw.setTime(now);
            if(map.containsKey(rechargeId)) {
                cpw.setCount(cpw.getCount()+1);
                cpw.setAmount(dbValue.getMoney().add(cpw.getAmount()));
            }else{
                cpw.setCount(1);
                cpw.setAmount(dbValue.getMoney());
            }
            map.put(rechargeId,cpw);
        }
        return new TySuccessResponse(map.values());
    }
}
