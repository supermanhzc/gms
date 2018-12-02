package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.BaseIdRequest;
import com.taoyuan.gms.model.dto.proxy.RechargeWithdrawRequest;
import com.taoyuan.gms.model.entity.proxy.FundExchangeEntity;
import com.taoyuan.gms.model.entity.proxy.RechargeWithdrawEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "充值提现")
@RequestMapping("/proxy/rechargewithdraw")
public interface RechargeWithdrawApi {

    /**
     * 查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    TyResponse getRechargeWithdraws(@RequestBody TyPageEntity page);

    /**
     * 充值或者提现
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TyResponse rechargeWithdraws(@RequestBody RechargeWithdrawRequest request);

    /**
     * 取消
     *
     * @param request
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public TyResponse cancel(@RequestBody BaseIdRequest request);

    /**
     * 处理
     *
     * @param request
     */
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public TyResponse process(@RequestBody BaseIdRequest request);
}
