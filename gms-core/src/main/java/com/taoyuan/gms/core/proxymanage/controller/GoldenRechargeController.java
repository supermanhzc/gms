package com.taoyuan.gms.core.proxymanage.controller;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyDateUtils;
import com.taoyuan.gms.api.proxy.GoldenRechargeApi;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.job.JobManager;
import com.taoyuan.gms.job.proxymanage.GoldenRechargeJob;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
public class GoldenRechargeController implements GoldenRechargeApi {

    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Override
    public TyResponse createGoldenRecharge(GoldenRechargeEntity entity) {
        entity.setTime(new Date());
        entity.setStatus(1);
        goldenRechargeService.save(entity);

        JobManager.addJob("GoldRecharge", GoldenRechargeJob.class, TyDateUtils.getCronAfterSeconds(5), entity);
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

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("当前时间：" + sdf.format(now));

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, 5);
        System.out.println(sdf.format(nowTime.getTime()));
    }
}
