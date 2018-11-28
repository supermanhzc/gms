package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.http.TyResponse;
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
     * @param map
     * @return
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    List<RechargeWithdrawEntity> getRechargeWithdraws(@RequestBody Map<String, Object> map);

    /**
     * 充值或者提现
     *
     * @param rechargeWithdrawEntity
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TyResponse rechargeWithdraws(@RequestBody RechargeWithdrawEntity rechargeWithdrawEntity);

    /**
     * 取消
     *
     * @param rechargeWithdrawEntity
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public TyResponse cancel(@RequestBody RechargeWithdrawEntity rechargeWithdrawEntity);

    /**
     * 取消
     *
     * @param rechargeWithdrawEntity
     */
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public TyResponse process(@RequestBody RechargeWithdrawEntity rechargeWithdrawEntity);
}
