package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.gms.api.admin.ExchargeApi;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.core.adminmanage.service.IExchargeCardPwdService;
import com.taoyuan.gms.core.adminmanage.service.IExchargeOrderService;
import com.taoyuan.gms.core.adminmanage.service.IPrizeService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeCardPwdEntity;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeOrderEntity;
import com.taoyuan.gms.model.entity.admin.prize.PrizeEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class ExchargeController extends BaseController implements ExchargeApi {

    @Autowired
    private IExchargeCardPwdService exchargeCardPwdService;

    @Autowired
    private IExchargeOrderService exchargeOrderService;

    @Autowired
    private IPrizeService prizeService;

    @Autowired
    private ICardPasswordService cardPasswordService;

    @Override
    public ExchargeOrderEntity create(ExchargeOrderEntity order) {
        if (null == order) {
            throw new ValidateException("对象不能为空。");
        }

        if (0 == order.getExchangeNum()) {
            throw new ValidateException("兑换数量不能为0。");
        }

        if (StringUtils.isEmpty(order.getPrizeName())) {
            throw new ValidateException("兑换商品名称不能为空。");
        }

        PrizeEntity prizeEntity = prizeService.getByName(order.getPrizeName());
        order.setExchangeNum(order.getExchangeNum());
        order.setExchangeSinglePrice(prizeEntity.getBasicPrice());
        order.setExchangeTime(new Date());
        order.setMemberId(getCurrentUserId());
        order.setMemberNickName(getById(getCurrentUserId()).getNickName());
        if (0 == prizeEntity.getAutoDispatch()) {
            //非自动发货，默认状态为未发货1
            order.setStatus(1);
            exchargeOrderService.save(order);
        } else {
            //TODO 自动发货将生成的卡发往会员账号

            //自动发货默认状态为2
            order.setStatus(2);
            exchargeOrderService.save(order);
        }

        return order;
    }

    @Override
    public ExchargeOrderEntity getExchangeOrder(Long id) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        return exchargeOrderService.getById(id);
    }

    @Override
    public IPage<Map<String, Object>> getExchangeOrders(Map<String, Object> map) {
        Page page = getPage(map);
        return exchargeOrderService.pageMaps(page, null);
    }

    @Override
    public ExchargeOrderEntity freeze(ExchargeOrderEntity order) {
        if (null == order) {
            throw new ValidateException("输入不能为空。");
        }

        if (0 == order.getOrderId()) {
            throw new ValidateException("订单号不能为空。");
        }

        //TODO 自动发货将生成的卡发往会员账号

        ExchargeOrderEntity dbValue = exchargeOrderService.getByOrderId(order.getOrderId());
        //设置状态为发货
        dbValue.setStatus(2);
        exchargeOrderService.saveOrUpdate(dbValue);
        return dbValue;
    }

    @Override
    public ExchargeOrderEntity cancel(ExchargeOrderEntity order) {
        if (null == order) {
            throw new ValidateException("输入不能为空。");
        }

        if (0 == order.getOrderId()) {
            throw new ValidateException("订单号不能为空。");
        }

        ExchargeOrderEntity dbValue = exchargeOrderService.getByOrderId(order.getOrderId());
        //设置状态为取消
        dbValue.setStatus(3);
        exchargeOrderService.saveOrUpdate(dbValue);
        return dbValue;
    }

    @Override
    public ExchargeCardPwdEntity create(ExchargeCardPwdEntity cardPwd) {
        if (StringUtils.isEmpty(cardPwd.getCardId())) {
            throw new ValidateException("卡密卡号不能为空。");
        }

        if (StringUtils.isEmpty(cardPwd.getCardPassword())) {
            throw new ValidateException("卡密密码不能为空。");
        }

        CardPasswordEntity cardPasswordEntity = cardPasswordService.getByCardId(cardPwd.getCardId());
        cardPwd.setCardType(cardPasswordEntity.getCardType());
        cardPwd.setStartTime(new Date());
        cardPwd.setMemberId(getCurrentUserId());
        cardPwd.setMemberNickName(getCurrentUserName());
        //默认1未兑换
        cardPwd.setStatus(1);
        exchargeCardPwdService.save(cardPwd);
        return cardPwd;
    }

    @Override
    public ExchargeCardPwdEntity getExchangeCardPwd(Long id) {
        return exchargeCardPwdService.getById(id);
    }

    @Override
    public IPage<Map<String, Object>> getExchangeCardPwds(Map<String, Object> map) {
        Page page = getPage(map);
        return exchargeCardPwdService.pageMaps(page, null);
    }

    @Override
    public ExchargeCardPwdEntity freeze(ExchargeCardPwdEntity cardPwd) {
        if (null == cardPwd) {
            throw new ValidateException("输入不能为空。");
        }

        //订单号不能为空
        if (cardPwd.getOrderId() == 0) {
            throw new ValidateException("订单号不能为空。");
        }

        ExchargeCardPwdEntity dbValue = exchargeCardPwdService.getByOrderId(cardPwd.getOrderId());
        dbValue.setStatus(2);
        exchargeCardPwdService.saveOrUpdate(dbValue);
        return dbValue;
    }

    @Override
    public ExchargeCardPwdEntity unfreeze(ExchargeCardPwdEntity cardPwd) {
        if (null == cardPwd) {
            throw new ValidateException("输入不能为空。");
        }

        if (cardPwd.getOrderId() == 0) {
            throw new ValidateException("订单号不能为空。");
        }

        ExchargeCardPwdEntity dbValue = exchargeCardPwdService.getByOrderId(cardPwd.getOrderId());
        dbValue.setStatus(3);
        exchargeCardPwdService.saveOrUpdate(dbValue);
        return dbValue;
    }
}
