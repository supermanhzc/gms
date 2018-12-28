package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.proxy.SaleStatisticStartEndRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "代理销售统计")
@RequestMapping("/proxy/salestatistic")
public interface SaleStatisticApi {
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    TyResponse getSaleStatistic(@RequestBody SaleStatisticStartEndRequest request);
}
