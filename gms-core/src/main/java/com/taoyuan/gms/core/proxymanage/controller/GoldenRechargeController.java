package com.taoyuan.gms.core.proxymanage.controller;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.GoldenRechargeApi;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

@RestController
public class GoldenRechargeController implements GoldenRechargeApi {

    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Override
    public TyResponse createGoldenRecharge(GoldenRechargeEntity entity) {
        entity.setTime(new Date());
        goldenRechargeService.save(entity);

        LocalTime time = LocalTime.now().plusMinutes(5);
        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse get5GoldenRecharge(Map<String, Object> map) {
        return null;
    }

    @Override
    public TyResponse withdraw(GoldenRechargeEntity entity) {
        return null;
    }
}
