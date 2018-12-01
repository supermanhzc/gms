package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.proxy.CardPassword;
import com.taoyuan.gms.model.entity.proxy.FundExchangeEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "资金互转")
@RequestMapping("/proxy/fundexchange")
public interface FundExchangeApi {

    @RequestMapping(value = "/getLatest10", method = RequestMethod.GET)
    List<FundExchangeEntity> getLatest10();

    @RequestMapping(value = "/getFundExchanges", method = RequestMethod.POST)
    List<FundExchangeEntity> getFundExchanges(@RequestBody TyPageEntity pageEntity);

    /**
     * 回收
     *
     * @param fundExchange
     * @return
     */
    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public TyResponse exchange(@RequestBody FundExchangeEntity fundExchange);
}
