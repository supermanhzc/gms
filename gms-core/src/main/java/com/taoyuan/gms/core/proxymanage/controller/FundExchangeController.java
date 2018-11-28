package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.FundExchangeApi;
import com.taoyuan.gms.core.adminmanage.controller.BaseController;
import com.taoyuan.gms.core.proxymanage.service.IFundExchangeService;
import com.taoyuan.gms.model.entity.proxy.FundExchangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class FundExchangeController extends BaseController implements FundExchangeApi {

    @Autowired
    private IFundExchangeService fundExchangeService;

    @Override
    public List<FundExchangeEntity> getLatest10() {
        return fundExchangeService.getLatest10().getRecords();
    }

    @Override
    public List<FundExchangeEntity> getFundExchanges(Map<String, Object> map) {
        Page page = getPage(map);
        return fundExchangeService.pageMaps(page, null).getRecords();
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
        fundExchangeService.save(fundExchange);
        //本代理账户减去转出金融
        updateBalance(proxyId, money);
        //对方代理账户加上转出金额
        updateBalance(fundExchange.getOpposite(), BigDecimal.ZERO.subtract(money));
        return new TySuccessResponse(fundExchange);
    }
}
