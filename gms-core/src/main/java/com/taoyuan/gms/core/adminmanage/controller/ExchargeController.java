package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.TyBusinessException;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.ExchargeApi;
import com.taoyuan.gms.common.util.StringUtil;
import com.taoyuan.gms.core.adminmanage.dao.ExchargeCardPwdMapper;
import com.taoyuan.gms.core.adminmanage.dao.ExchargeOrderMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.core.adminmanage.service.IExchargeCardPwdService;
import com.taoyuan.gms.core.adminmanage.service.IExchargeOrderService;
import com.taoyuan.gms.core.adminmanage.service.IPrizeService;
import com.taoyuan.gms.model.dto.admin.excharge.ExchargeOrderRequest;
import com.taoyuan.gms.model.dto.admin.excharge.ExchargeOrderCreateRequest;
import com.taoyuan.gms.model.entity.admin.card.CardPasswordEntity;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeCardPwdEntity;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeOrderEntity;
import com.taoyuan.gms.model.entity.admin.prize.PrizeEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@Slf4j
@RestController
public class ExchargeController extends BaseGmsController implements ExchargeApi {

    @Autowired
    private IExchargeCardPwdService exchargeCardPwdService;

    @Autowired
    private ExchargeCardPwdMapper exchargeCardPwdMapper;

    @Autowired
    private IExchargeOrderService exchargeOrderService;

    @Autowired
    private ExchargeOrderMapper exchargeOrderMapper;

    @Autowired
    private IPrizeService prizeService;

    @Autowired
    private ICardPasswordService cardPasswordService;

    @Override
    public TyResponse create(@RequestBody ExchargeOrderCreateRequest order) {
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
        if (null == prizeEntity) {
            throw new ValidateException("奖品不存在。");
        }

        ExchargeOrderEntity entity = new ExchargeOrderEntity();
        try {
            BeanUtils.copyProperties(order, entity);
        } catch (IllegalAccessException e) {
            throw new TyBusinessException("非法参数异常。");
        } catch (InvocationTargetException e) {
            throw new TyBusinessException("未知异常。");
        }
        order.setExchangeNum(order.getExchangeNum());
        entity.setExchangeSinglePrice(prizeEntity.getBasicPrice());
        entity.setStartTime(new Date());
        entity.setMemberId(getCurrentUserId());
        entity.setMemberNickName(getCurrentUserName());
        if (0 == prizeEntity.getAutoDispatch()) {
            //非自动发货，默认状态为未发货1
            entity.setStatus(1);
            exchargeOrderService.save(entity);
        } else {
            //TODO 自动发货将生成的卡发往会员账号

            //自动发货默认状态为2
            entity.setStatus(2);
            entity.setEndTime(new Date());
            exchargeOrderService.save(entity);
        }

        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse getExchangeOrder(Integer id) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        return new TySuccessResponse(exchargeOrderService.getByOrderId(id));
    }

    @Override
    public TyResponse getExchangeOrders(@RequestBody ExchargeOrderRequest request) {
        Page page = getPage(request);

        QueryWrapper<ExchargeOrderEntity> wrapper = new QueryWrapper<ExchargeOrderEntity>();
        String keyword = request.getKeyword();
        if (!StringUtils.isEmpty(keyword) && !StringUtils.isEmpty(keyword.trim())) {
            if (StringUtil.isNumber(keyword)) {
                //数字的话需要联合查询，id和昵称
                wrapper.lambda().eq(ExchargeOrderEntity::getMemberNickName, keyword).or().eq
                        (ExchargeOrderEntity::getMemberId, Long.valueOf(keyword));
            } else {
                //不是数字，只是查询昵称
                wrapper.lambda().eq(ExchargeOrderEntity::getMemberNickName, keyword);
            }
        }

        return new TySuccessResponse(exchargeOrderMapper.selectMapsPage(page, wrapper));
    }

    @Override
    public TyResponse sipping(@RequestBody ExchargeOrderEntity order) {
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
        dbValue.setProcessorId(getCurrentUserId());
        dbValue.setProcessorName(getCurrentUserName());
        dbValue.setEndTime(new Date());
        exchargeOrderService.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse cancel(@RequestBody ExchargeOrderEntity order) {
        if (null == order) {
            throw new ValidateException("输入不能为空。");
        }

        if (0 == order.getOrderId()) {
            throw new ValidateException("订单号不能为空。");
        }

        ExchargeOrderEntity dbValue = exchargeOrderService.getByOrderId(order.getOrderId());
        //设置状态为取消
        dbValue.setStatus(3);
        dbValue.setProcessorId(getCurrentUserId());
        dbValue.setProcessorName(getCurrentUserName());
        dbValue.setEndTime(new Date());
        exchargeOrderService.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse create(@RequestBody ExchargeCardPwdEntity cardPwd) {
        if (StringUtils.isEmpty(cardPwd.getCardId())) {
            throw new ValidateException("卡密卡号不能为空。");
        }

        if (StringUtils.isEmpty(cardPwd.getCardPassword())) {
            throw new ValidateException("卡密密码不能为空。");
        }

        CardPasswordEntity cardPasswordEntity = cardPasswordService.getByCardId(cardPwd.getCardId());
        if (null == cardPasswordEntity) {
            throw new ValidateException("卡密不存在。");
        }
        cardPwd.setCardType(cardPasswordEntity.getCardType());
        cardPwd.setStartTime(new Date());
        cardPwd.setMemberId(getCurrentUserId());
        cardPwd.setMemberNickName(getCurrentUserName());
        //默认1未兑换
        cardPwd.setStatus(1);
        exchargeCardPwdService.save(cardPwd);
        return new TySuccessResponse(cardPwd);
    }

    @Override
    public TyResponse getExchangeCardPwd(Long id) {
        return new TySuccessResponse(exchargeCardPwdService.getById(id));
    }

    @Override
    public TyResponse getExchangeCardPwds(@RequestBody TyPageEntity pageEntity) {
        Page page = getPage(pageEntity);
        return new TySuccessResponse(exchargeCardPwdMapper.selectMapsPage(page, null));
    }

    @Override
    public TyResponse freeze(@RequestBody ExchargeCardPwdEntity cardPwd) {
        if (null == cardPwd) {
            throw new ValidateException("输入不能为空。");
        }

        //订单号不能为空
        if (cardPwd.getOrderId() == 0) {
            throw new ValidateException("订单号不能为空。");
        }

        ExchargeCardPwdEntity dbValue = exchargeCardPwdService.getByOrderId(cardPwd.getOrderId());
        dbValue.setStatus(2);
        dbValue.setWithdrawProxyId(getCurrentUserId());
        dbValue.setWithdrawProxyName(getCurrentUserName());
        dbValue.setEndTime(new Date());
        exchargeCardPwdService.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse unfreeze(@RequestBody ExchargeCardPwdEntity cardPwd) {
        if (null == cardPwd) {
            throw new ValidateException("输入不能为空。");
        }

        if (cardPwd.getOrderId() == 0) {
            throw new ValidateException("订单号不能为空。");
        }

        ExchargeCardPwdEntity dbValue = exchargeCardPwdService.getByOrderId(cardPwd.getOrderId());
        if (null == dbValue) {
            throw new ValidateException("卡密不存在。");
        }
        dbValue.setStatus(3);
        dbValue.setWithdrawProxyId(getCurrentUserId());
        dbValue.setWithdrawProxyName(getCurrentUserName());
        dbValue.setEndTime(new Date());
        exchargeCardPwdService.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }
}
