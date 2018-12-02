package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.FundExchangeApi;
import com.taoyuan.gms.core.proxymanage.dao.FundExchangeMapper;
import com.taoyuan.gms.core.proxymanage.service.IFundExchangeService;
import com.taoyuan.gms.model.entity.proxy.FundExchangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class FundExchangeController extends BaseGmsProxyController implements FundExchangeApi {

    @Autowired
    private IFundExchangeService service;

    @Autowired
    private FundExchangeMapper mapper;

    @Override
    public TyResponse getLatest10() {
        return new TySuccessResponse(service.getLatest10().getRecords());
    }

    @Override
    public TyResponse getFundExchanges(TyPageEntity pageEntity) {
        Page page = getPage(pageEntity);
        return new TySuccessResponse(mapper.selectPage(page, null));
    }

    @Override
    public TyResponse exchange(FundExchangeEntity fundExchange) {
        if (null == fundExchange) {
            throw new ValidateException("对象不能为空。");
        }

        if (null == fundExchange.getOpposite()) {
            throw new ValidateException("转出代理不能为空。");
        }

        Long proxyId = TySession.getCurrentUser().getUserId();
        BigDecimal money = fundExchange.getMoney();
        if (null == fundExchange.getMoney()) {
            throw new ValidateException("转出金额不能为空。");
        } else if (money.compareTo(getBalance(proxyId)) > 0) {
            throw new ValidateException("转出金额必须小于账户余额。");
        }

        fundExchange.setType(1);
        fundExchange.setProxyId(proxyId);
        fundExchange.setTime(new Date());
        service.save(fundExchange);

        //对方代理账户加上转出金额
        updateBalance(fundExchange.getOpposite(), money);

        //记录日志
        recordOperation(6,"代理互转",money);
        return new TySuccessResponse(fundExchange);
    }
}
