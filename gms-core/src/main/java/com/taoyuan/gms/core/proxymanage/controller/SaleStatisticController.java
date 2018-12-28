package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.SaleStatisticApi;
import com.taoyuan.gms.core.proxymanage.service.IProxySaleStatisticService;
import com.taoyuan.gms.model.dto.proxy.SaleStatisticStartEndRequest;
import com.taoyuan.gms.model.entity.proxy.ProxySaleStatisticEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SaleStatisticController extends BaseGmsProxyController implements SaleStatisticApi {
    @Autowired
    private IProxySaleStatisticService proxySaleStatisticService;

    @Override
    public TyResponse getSaleStatistic(@RequestBody SaleStatisticStartEndRequest request) {
        Page page = getPage(request);

        QueryWrapper<ProxySaleStatisticEntity> wrapper = new QueryWrapper<ProxySaleStatisticEntity>();
        wrapper.lambda().eq(ProxySaleStatisticEntity::getProxyId, getCurrentUserId());

        LocalDate start = request.getStart();
        LocalDate end = request.getEnd();
        if (null != start && null != end) {
            wrapper.lambda().between(ProxySaleStatisticEntity::getDate, start, end);
        }

        return new TySuccessResponse(proxySaleStatisticService.pageMaps(page, wrapper));
    }
}
