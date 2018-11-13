package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.util.TyBigNumUtil;
import com.taoyuan.gms.api.admin.RecordsQueryApi;
import com.taoyuan.gms.core.adminmanage.service.*;
import com.taoyuan.gms.model.dto.admin.DailyStatisticDto;
import com.taoyuan.gms.model.entity.admin.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
            entity.setType(1);
            list.add(entity);
            chartsRewardsService.save(entity);
        }

        QueryWrapper<ChartsRewardsEntity> wrapper = new QueryWrapper<ChartsRewardsEntity>();
        wrapper.eq("type", 1);
        return chartsRewardsService.pageMaps(new Page<ChartsRewardsEntity>(pageIndex, pageSize), wrapper);
    }

    @Override
    public IPage<Map<String, Object>> getChartsRewards(String id, String type) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> getVChartsRewards(Integer pageIndex, Integer pageSize) {
        List<ChartsRewardsEntity> list = new ArrayList<ChartsRewardsEntity>();
        for (int i = 0; i < 10; i++) {
            ChartsRewardsEntity entity = new ChartsRewardsEntity();
            entity.setMemberId(300001l);
            entity.setMemberNickName("会员1");
            entity.setRewards(5555d);
            entity.setStatus("未领取");
            entity.setTime(new Date());
            entity.setType(2);
            list.add(entity);
            chartsRewardsService.save(entity);
        }

        QueryWrapper<ChartsRewardsEntity> wrapper = new QueryWrapper<ChartsRewardsEntity>();
        wrapper.eq("type", 2);
        return chartsRewardsService.pageMaps(new Page<ChartsRewardsEntity>(pageIndex, pageSize), wrapper);
    }

    @Override
    public IPage<Map<String, Object>> getVChartsRewards(String id, String type) {
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
    public DailyStatisticDto getDailyStatistic() {
        //TODO需要从各个表实时查询，只查询当日
        DailyStatisticDto dto = new DailyStatisticDto();
        dto.setDate(LocalDate.now().toString());
        dto.setRegisterMemberNum(10);
        dto.setMembersBalance(TyBigNumUtil.cvrtNum2String(111111111.02222d));
        dto.setProxyBalance(TyBigNumUtil.cvrtNum2String(10001234123412340.23232d ));
        dto.setGameProfitLoss(TyBigNumUtil.cvrtNum2String(1001234230.00d ));
        dto.setExchange(TyBigNumUtil.cvrtNum2String(5555547456d ));
        dto.setSubstitute(TyBigNumUtil.cvrtNum2String(567442534528.00d ));
        dto.setExchangePoundage(TyBigNumUtil.cvrtNum2String(321.00d ));
        dto.setCdKeyRecharge(TyBigNumUtil.cvrtNum2String(13451345d ));
        dto.setChartsReward(TyBigNumUtil.cvrtNum2String(1313d ));
        dto.setChipinCommission(TyBigNumUtil.cvrtNum2String(2222d ));
        dto.setFirstChargeRebate(TyBigNumUtil.cvrtNum2String(22222d ));
        dto.setLossRebate(TyBigNumUtil.cvrtNum2String(33333d ));
        dto.setChipinWage(TyBigNumUtil.cvrtNum2String(123d ));
        return dto;
    }

    @Override
    public IPage<Map<String, Object>> getAdminLogins(Integer pageIndex, Integer pageSize) {
        log.info("getAdminLogins pageIndex={}, pageSize={}", pageIndex, pageSize);
        validatePageParams(pageIndex, pageSize);
        for (int i = 0; i < 10; i++) {
            UserLoginEntity entity = new UserLoginEntity();
            entity.setMemberId(300002l);
            entity.setMemberNickName("admin");
            entity.setIp("181.1.1.200");
            entity.setAddr("江苏省南京市");
            entity.setStatus(1);
            entity.setType(3);
            memberLoginService.save(entity);
        }
        Page page = new Page<UserLoginEntity>(pageIndex, pageSize);
        if (-1 == pageIndex) {
            page = null;
        }

        QueryWrapper<UserLoginEntity> wrapper = new QueryWrapper<UserLoginEntity>();
        wrapper.eq("type", 3);
        return memberLoginService.pageMaps(page, wrapper);

    }

    @Override
    public IPage<Map<String, Object>> getMemberLogins(Integer pageIndex, Integer pageSize) {
        log.info("getMemberLogins pageIndex={}, pageSize={}", pageIndex, pageSize);
        validatePageParams(pageIndex, pageSize);
        for (int i = 0; i < 10; i++) {
            UserLoginEntity entity = new UserLoginEntity();
            entity.setMemberId(300002l);
            entity.setMemberNickName("会员1");
            entity.setIp("181.1.1.200");
            entity.setAddr("江苏省南京市");
            entity.setStatus(1);
            entity.setType(1);
            memberLoginService.save(entity);
        }

        QueryWrapper<UserLoginEntity> wrapper = new QueryWrapper<UserLoginEntity>();
        wrapper.eq("type", 1);
        return memberLoginService.pageMaps(new Page<UserLoginEntity>(pageIndex, pageSize), wrapper);
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

    public static void main(String[] args) {
        System.out.println(new Date().toString());
    }
}
