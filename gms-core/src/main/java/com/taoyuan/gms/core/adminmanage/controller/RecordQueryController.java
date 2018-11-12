package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.gms.api.admin.RecordsQueryApi;
import com.taoyuan.gms.core.adminmanage.service.*;
import com.taoyuan.gms.model.entity.admin.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class RecordQueryController implements RecordsQueryApi {

    @Autowired
    private IVerificationCodeService verificationCodeService;

    @Autowired
    private ISubstituteService substituteService;

    @Autowired
    private IChartsRewardsService chartsRewardsService;

    @Autowired
    private ILossRabateService lossRabateService;

    @Autowired
    private IProxyOperService proxyOperService;

    @Autowired
    private IMemberLoginService memberLoginService;

    @Override
    public IPage<Map<String, Object>> getVerificationCodes(Integer pageIndex, Integer pageSize) {
        validatePageParams(pageIndex, pageSize);

//        VerificationCodeEntity entity= new VerificationCodeEntity();
//        entity.setInfName("短信");
//        entity.setType("注册");
//        entity.setVCode("888888");
//        verificationCodeService.save(entity);
        return verificationCodeService.pageMaps(new Page<VerificationCodeEntity>(pageIndex, pageSize), null);
    }

    @Override
    public IPage<Map<String, Object>> getSubstitutes(Integer pageIndex, Integer pageSize) {
        validatePageParams(pageIndex, pageSize);
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
        validatePageParams(pageIndex, pageSize);
        for (int i = 0; i < 10; i++) {
            ProxyOperEntity entity = new ProxyOperEntity();
            entity.setProxyId(3l);
            entity.setProxyName("代理1");
            entity.setMoneyChanged(-100d);
            entity.setAccount(1000d);
            proxyOperService.save(entity);
        }

        return proxyOperService.pageMaps(new Page<ProxyOperEntity>(pageIndex, pageSize), null);
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
    public IPage<Map<String, Object>> getLossRebates(Integer pageIndex, Integer pageSize) {
        validatePageParams(pageIndex, pageSize);
        List<LossRabateEntity> list = new ArrayList<LossRabateEntity>();
        for (int i = 0; i < 10; i++) {
            LossRabateEntity entity = new LossRabateEntity();
            entity.setMemberId(300001l);
            entity.setMemberNickName("会员1");
            entity.setLoss(50000d);
            entity.setRabate(10000d);
            entity.setTime(new Date());
            entity.setStatus(1);
            list.add(entity);
            lossRabateService.save(entity);
        }
        return lossRabateService.pageMaps(new Page<LossRabateEntity>(pageIndex, pageSize), null);
    }

    @Override
    public IPage<Map<String, Object>> getLossRebates(/*Integer pageIndex, Integer pageSize, Long id, int status*/
            PageConditionEntity pageConditionEntity) {
        int pageIndex = pageConditionEntity.getPageIndex();
        int pageSize = pageConditionEntity.getPageSize();
        Long id = pageConditionEntity.getId();
        int status = pageConditionEntity.getStatus();
        log.info("Page index={}, size={}, id={}, status={}", pageIndex, pageSize, id, status);
        validatePageParams(pageIndex, pageSize);
        QueryWrapper<LossRabateEntity> wrapper = new QueryWrapper<LossRabateEntity>();
        if (null == id) {
            if (-1 == status) {
                wrapper = null;
            } else {
                wrapper.eq("status", status);
            }
        } else {
            wrapper.eq("memberId", id);
            if (-1 != status) {
                wrapper.and(wrapperTmp -> wrapperTmp.eq("status", status));
            }
        }
        return lossRabateService.pageMaps(new Page<LossRabateEntity>(pageIndex, pageSize), wrapper);
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
    public IPage<Map<String, Object>> getMemberLogins(Integer pageIndex, Integer pageSize) {
        validatePageParams(pageIndex, pageSize);
        for (int i = 0; i < 10; i++) {
            MemberLoginEntity entity = new MemberLoginEntity();
            entity.setMemberId(300002l);
            entity.setMemberNickName("会员1");
            entity.setIp("181.1.1.200");
            entity.setAddr("江苏省南京市");
            entity.setStatus(1);
            memberLoginService.save(entity);
        }

        return memberLoginService.pageMaps(new Page<MemberLoginEntity>(pageIndex, pageSize), null);
    }

    @Override
    public IPage<Map<String, Object>> getMemberLogins(String id, String type) {
        return null;
    }

    private void validatePageParams(Integer pageIndex, Integer pageSize) {
        if (null == pageIndex) {
            throw new ValidateException(10000001, "分页参数不能为空。", "pageIndex");
        } else if (null == pageSize) {
            throw new ValidateException(10000002, "分页参数不能为空。", "pageSize");
        }
    }
}
