package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.gms.api.admin.RecordsQueryApi;
import com.taoyuan.gms.core.adminmanage.service.IChartsRewardsService;
import com.taoyuan.gms.core.adminmanage.service.ISubstituteService;
import com.taoyuan.gms.core.adminmanage.service.IVerificationCodeService;
import com.taoyuan.gms.model.entity.admin.ChartsRewardsEntity;
import com.taoyuan.gms.model.entity.admin.SubstituteEntity;
import com.taoyuan.gms.model.entity.admin.VerificationCodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class RecordQueryController implements RecordsQueryApi {

    @Autowired
    private IVerificationCodeService verificationCodeService;

    @Autowired
    private ISubstituteService substituteService;

    @Autowired
    private IChartsRewardsService chartsRewardsService;

    @Override
    public IPage<Map<String, Object>> getVerificationCodes(Integer pageIndex, Integer pageSize) {
        if (null == pageIndex) {
            throw new ValidateException(10000001, "分页参数不能为空。", "pageIndex");
        } else if (null == pageSize) {
            throw new ValidateException(10000002, "分页参数不能为空。", "pageSize");
        }

//        VerificationCodeEntity entity= new VerificationCodeEntity();
//        entity.setInfName("短信");
//        entity.setType("注册");
//        entity.setVCode("888888");
//        verificationCodeService.save(entity);
        return verificationCodeService.pageMaps(new Page<VerificationCodeEntity>(pageIndex, pageSize), null);
    }

    @Override
    public IPage<Map<String, Object>> getSubstitutes(Integer pageIndex, Integer pageSize) {
        List<SubstituteEntity> list = new ArrayList<SubstituteEntity>();
        for (int i = 0; i < 10; i++) {
            SubstituteEntity entity = new SubstituteEntity();
            entity.setMemberId(300001l);
            entity.setMemberNickName("会员1");
            entity.setProxyId(3l);
            entity.setProxyName("代理1");
            entity.setMoney(100d);
            entity.setStatus("提交");
            list.add(entity);
            substituteService.save(entity);
        }
        return substituteService.pageMaps(new Page<SubstituteEntity>(pageIndex, pageSize), null);
    }

    @Override
    public IPage<Map<String, Object>> getProxyOperates(Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getSaleStatistics(Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getSaleDetails(Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getSaleDetail(String name, String start, String end) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getLossRebates() {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getLossRebates(String id, String type) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getChartsRewards(Integer pageIndex, Integer pageSize) {
        List<ChartsRewardsEntity> list = new ArrayList<ChartsRewardsEntity>();
        for (int i = 0; i < 10; i++) {
            ChartsRewardsEntity entity = new ChartsRewardsEntity();
            entity.setMemberId(300001l);
            entity.setMemberNickName("会员1");
            entity.setRewards(5555d);
            entity.setStatus("未领取");
            entity.setTime(new Date());
            list.add(entity);
            chartsRewardsService.save(entity);
        }

        return chartsRewardsService.pageMaps(new Page<ChartsRewardsEntity>(pageIndex, pageSize), null);
    }

    @Override
    public IPage<Map<String, Object>> getChartsRewards(String id, String type) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getChipinWages(Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getChipinWages(String id, String type) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getJuniorCommissions() {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getJuniorCommissions(String id, String type) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getFirstchargeRebates() {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getFirstchargeRebates(String id, String type) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getDailyStatistic(String date) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getMemberLogins() {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getMemberLogins(String id, String type) {
        return null;
    }
}