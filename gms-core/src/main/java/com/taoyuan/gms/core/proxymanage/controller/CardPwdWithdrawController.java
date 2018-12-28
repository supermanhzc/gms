package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.CardPwdWithdrawApi;
import com.taoyuan.gms.common.util.StringUtil;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.core.proxymanage.service.ICardPwdWithdrawService;
import com.taoyuan.gms.model.dto.BaseKeywordPageRequest;
import com.taoyuan.gms.model.entity.admin.card.CardPasswordEntity;
import com.taoyuan.gms.model.entity.proxy.CardPassword;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@RestController
public class CardPwdWithdrawController extends BaseGmsProxyController implements CardPwdWithdrawApi {

    @Autowired
    private ICardPwdWithdrawService service;

    @Autowired
    private ICardPasswordService cpService;

    @Autowired
    private TyUserService userService;

    @Override
    @OperControllerLog(module = "代理卡密回收管理", type = "查询代理卡密最近10笔回收记录")
    public TyResponse getLatest10() {
        return new TySuccessResponse(service.getLatest10());
    }

    @Override
    @OperControllerLog(module = "代理卡密回收管理", type = "撤销代理卡密")
    public TyResponse withdraw(@RequestBody List<CardPassword> cardPasswordList) {
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
                    dbValue.setOwner(getCurrentUserName());
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

            // 给用户充值，给用户加上充值额度
            Iterator entries = map.entrySet().iterator();
            BigDecimal totalMoney = BigDecimal.ZERO;
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String rechargeId = (String) entry.getKey();
                CardPwdWithdrawEntity cpw = (CardPwdWithdrawEntity) entry.getValue();
                totalMoney.add(cpw.getAmount());
                updateBalance(Long.valueOf(rechargeId), cpw.getAmount());
            }

            //全部完成后更改卡状态为已回收
            cpService.saveOrUpdateBatch(dbList);

            //记录日志
            recordOperation(5, "回收卡密", totalMoney);
            return new TySuccessResponse(map.values());
        }

        return new TySuccessResponse(null);
    }

    @Override
    @OperControllerLog(module = "代理卡密回收管理", type = "查询代理卡密")
    public TyResponse retrieve(@RequestBody BaseKeywordPageRequest request) {
        Page page = getPage(request);
        QueryWrapper<CardPasswordEntity> queryWrapper = new QueryWrapper<CardPasswordEntity>();
        queryWrapper.lambda().eq(CardPasswordEntity::getCreateUser, getCurrentUserId()).eq(CardPasswordEntity::getStatus, 2);
        queryWrapper.lambda().orderByDesc(CardPasswordEntity::getEndTime);

        String keyword = request.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            if (StringUtil.isNumber(keyword)) {
                Long id = Long.valueOf(keyword);
                queryWrapper.and(wrapper -> wrapper.eq("card_id", keyword).or().eq("card_password",
                        keyword).or().eq("recharge_id", id));
            } else {
                queryWrapper.and(wrapper -> wrapper.eq("card_id", keyword));
            }
        }

        return new TySuccessResponse(cpService.pageMaps(page, queryWrapper));
    }

    @Override
    @OperControllerLog(module = "代理卡密回收管理", type = "查询代理卡密")
    public TyResponse records(@RequestBody BaseKeywordPageRequest request) {
        Page page = getPage(request);
        QueryWrapper<CardPasswordEntity> cardPwdWrapper = new QueryWrapper<CardPasswordEntity>();
        cardPwdWrapper.lambda().eq(CardPasswordEntity::getOwner, getCurrentUserName());
        cardPwdWrapper.lambda().orderByDesc(CardPasswordEntity::getEndTime);

        String keyword = request.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            if (StringUtil.isNumber(keyword)) {
                Long id = Long.valueOf(keyword);
                cardPwdWrapper.and(wrapper -> wrapper.eq("card_id", id).or().eq("card_password",
                        keyword).or().eq("recharge_id", id));
            } else {
                cardPwdWrapper.and(wrapper -> wrapper.eq("card_id", keyword));
            }
        }
        return new TySuccessResponse(cpService.pageMaps(page, cardPwdWrapper));
    }
}
