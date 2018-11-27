package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.aaa.service.TyUserLoginService;
import com.taoyuan.framework.common.entity.TyProxyOperation;
import com.taoyuan.framework.common.entity.TyUserLoginEntity;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.util.TyBigNumUtil;
import com.taoyuan.framework.common.util.TyPageUtil;
import com.taoyuan.framework.mail.TyVerificationCodeService;
import com.taoyuan.framework.oper.IProxyOperService;
import com.taoyuan.gms.api.admin.RecordsQueryApi;
import com.taoyuan.gms.common.util.StringUtil;
import com.taoyuan.gms.core.adminmanage.service.*;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.model.dto.admin.DailyStatisticDto;
import com.taoyuan.gms.model.entity.admin.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@RestController
public class RecordQueryController extends BaseController implements RecordsQueryApi {

    @Autowired
    private TyVerificationCodeService verificationCodeService;

    @Autowired
    private IChartsRewardsService chartsRewardsService;

    @Autowired
    private ILossRabateService lossRabateService;

    @Autowired
    private IProxyOperService proxyOperService;

    @Autowired
    private TyUserLoginService userLoginService;

    @Autowired
    private ISaleDetailService saleDetailService;

    @Autowired
    private IChipinWageService chipinWageService;

    @Autowired
    private IJuniorCommissionService juniorCommissionService;

    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Override
    public IPage<Map<String, Object>> getVerificationCodes(Integer pageIndex, Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
//        VerificationCodeEntity entity= new VerificationCodeEntity();
//        entity.setInfName("短信");
//        entity.setType("注册");
//        entity.setVCode("888888");
//        verificationCodeService.save(entity);
        return verificationCodeService.pageMaps(page, null);
    }

    @Override
    public IPage<Map<String, Object>> getRecharges(Integer pageIndex, Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        return goldenRechargeService.pageMaps(page, null);
    }

    @Override
    public IPage<Map<String, Object>> getProxyOperates(Integer pageIndex, Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        return proxyOperService.pageMaps(new Page<TyProxyOperation>(pageIndex, pageSize), null);
    }

    @Override
    public List<SaleDetailEntity> getSaleStatistics(Map<String, Object> map) {
        Page page = getPage(map);
        QueryWrapper<SaleDetailEntity> wrapper = new QueryWrapper<SaleDetailEntity>();
        if (map.containsKey("begin")) {
            String begin = String.valueOf(map.get("begin"));
            if (map.containsKey("end")) {
                String end = String.valueOf(map.get("end"));
                wrapper.between("time", begin, end);
            } else {
                throw new ValidateException(10000010, "请选择结束时间。", null);
            }
        } else {
            if (map.containsKey("end")) {
                throw new ValidateException(10000010, "请选择开始时间。", null);
            }
        }
        List<SaleDetailEntity> entityList = saleDetailService.list(wrapper);
        Map<String, SaleDetailEntity> rsltMap = new HashMap<String, SaleDetailEntity>();
        for (SaleDetailEntity entity : entityList) {
            if (rsltMap.containsKey(entity.getProxyName())) {
                SaleDetailEntity saleDetailEntity = rsltMap.get(entity.getProxyName());
                saleDetailEntity.setCallbackMoney(saleDetailEntity.getCallbackMoney() + entity.getCallbackMoney());
                saleDetailEntity.setSubstituteMoney(saleDetailEntity.getSubstituteMoney() + entity.getSubstituteMoney());
                saleDetailEntity.setIncome(saleDetailEntity.getIncome() + entity.getIncome());
                continue;
            }
            rsltMap.put(entity.getProxyName(), entity);
        }

        List<SaleDetailEntity> rsltList = new ArrayList<SaleDetailEntity>(rsltMap.values());
        return rsltList;
    }

    @Override
    public IPage<Map<String, Object>> getSaleDetails(Map<String, Object> map) {
        Page page = getPage(map);

        QueryWrapper<SaleDetailEntity> wrapper = new QueryWrapper<SaleDetailEntity>();
        if (map.containsKey("proxyName")) {
            String name = map.get("proxyName").toString();
            wrapper.eq("proxyName", name);
        }

        if (map.containsKey("begin")) {
            String begin = String.valueOf(map.get("begin"));
            if (map.containsKey("end")) {
                String end = String.valueOf(map.get("end"));
                wrapper.between("time", begin, end);
            } else {
                throw new ValidateException(10000010, "请选择结束时间。", null);
            }
        } else {
            if (map.containsKey("end")) {
                throw new ValidateException(10000010, "请选择开始时间。", null);
            }
        }

        return saleDetailService.pageMaps(page, wrapper);
    }

    @Override
    public IPage<Map<String, Object>> getLossRebates(Integer pageIndex, Integer pageSize) {
        super.validatePageParams(pageIndex, pageSize);
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
    public IPage<Map<String, Object>> getLossRebates(@RequestBody Map<String, Object> map) {
        //TODO 测试代码
        for (int i = 0; i < 10; i++) {
            LossRabateEntity entity = new LossRabateEntity();
            entity.setMemberId(300001l);
            entity.setMemberNickName("会员1");
            entity.setLoss(50000d);
            entity.setRabate(10000d);
            entity.setTime(new Date());
            entity.setStatus(1);
            lossRabateService.save(entity);
        }

        Page page = TyPageUtil.getPage(map);
        QueryWrapper<LossRabateEntity> wrapper = new QueryWrapper<LossRabateEntity>();
        if (map.containsKey("id")) {
            wrapper.lambda().eq(LossRabateEntity::getMemberId, Long.valueOf(map.get("id").toString()));
            if (map.containsKey("status")) {
                wrapper.lambda().eq(LossRabateEntity::getStatus, Integer.valueOf(map.get("status").toString()));
            }
        } else {
            if (map.containsKey("status")) {
                wrapper.lambda().eq(LossRabateEntity::getStatus, Integer.valueOf(map.get("status").toString()));
            }
        }

        return lossRabateService.pageMaps(page, wrapper);
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

        Page page = getPage(pageIndex, pageSize);
        QueryWrapper<ChartsRewardsEntity> wrapper = new QueryWrapper<ChartsRewardsEntity>();
        wrapper.eq("type", 2);
        return chartsRewardsService.pageMaps(page, wrapper);
    }

    @Override
    public IPage<Map<String, Object>> getVChartsRewards(String id, String type) {
        return null;
    }


    @Override
    public IPage<Map<String, Object>> getChipinWages(Map<String, Object> map) {
        //TODO 测试数据
        for (int i = 0; i < 10; i++) {
            ChipinWageEntity entity = new ChipinWageEntity();
            entity.setMemberId(300001l);
            entity.setMemberNickName("会员1");
            entity.setWage(1000);
            entity.setEffectiveFlow(10000);
            entity.setDate(new Date());
            entity.setStatus(1);
            chipinWageService.save(entity);
        }

        Page page = getPage(map);
        QueryWrapper<ChipinWageEntity> wrapper = new QueryWrapper();
        long id = 0l;
        if (map.containsKey("id")) {
            id = Long.valueOf(map.get("id").toString());
            wrapper.lambda().eq(ChipinWageEntity::getMemberId, id);
        }

        int status = 0;
        if (map.containsKey("status")) {
            status = Integer.valueOf(map.get("status").toString());
            wrapper.lambda().eq(ChipinWageEntity::getStatus, status);
        }

        return chipinWageService.pageMaps(page, wrapper);
    }

    @Override
    public IPage<Map<String, Object>> getJuniorCommissions(Map<String, Object> map) {
        //TODO 测试数据
        for (int i = 0; i < 10; i++) {
            JuniorCommissionEntity entity = new JuniorCommissionEntity();
            entity.setMemberId(300001l);
            entity.setMemberNickName("会员1");
            entity.setWage(1000);
            entity.setYtdJuniorCommissionFlow(10000);
            entity.setDate(new Date());
            entity.setStatus(1);
            juniorCommissionService.save(entity);
        }

        Page page = getPage(map);
        QueryWrapper<JuniorCommissionEntity> wrapper = new QueryWrapper();
        long id = 0l;
        if (map.containsKey("id")) {
            id = Long.valueOf(map.get("id").toString());
            wrapper.lambda().eq(JuniorCommissionEntity::getMemberId, id);
        }

        int status = 0;
        if (map.containsKey("status")) {
            status = Integer.valueOf(map.get("status").toString());
            wrapper.lambda().eq(JuniorCommissionEntity::getStatus, status);
        }

        return juniorCommissionService.pageMaps(page, wrapper);
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
        dto.setProxyBalance(TyBigNumUtil.cvrtNum2String(10001234123412340.23232d));
        dto.setGameProfitLoss(TyBigNumUtil.cvrtNum2String(1001234230.00d));
        dto.setExchange(TyBigNumUtil.cvrtNum2String(5555547456d));
        dto.setSubstitute(TyBigNumUtil.cvrtNum2String(567442534528.00d));
        dto.setExchangePoundage(TyBigNumUtil.cvrtNum2String(321.00d));
        dto.setCdKeyRecharge(TyBigNumUtil.cvrtNum2String(13451345d));
        dto.setChartsReward(TyBigNumUtil.cvrtNum2String(1313d));
        dto.setChipinCommission(TyBigNumUtil.cvrtNum2String(2222d));
        dto.setFirstChargeRebate(TyBigNumUtil.cvrtNum2String(22222d));
        dto.setLossRebate(TyBigNumUtil.cvrtNum2String(33333d));
        dto.setChipinWage(TyBigNumUtil.cvrtNum2String(123d));
        return dto;
    }

    @Override
    public IPage<Map<String, Object>> getAdminLogins(HashMap<String, Object> map) {
        log.info("getAdminLogins map={}", map);
        Page page = TyPageUtil.getPage(map);

        QueryWrapper<TyUserLoginEntity> wrapper = new QueryWrapper<TyUserLoginEntity>();
        wrapper.eq("type", 3);
        return userLoginService.pageMaps(page, wrapper);

    }

    @Override
    public IPage<Map<String, Object>> getMemberLogins(HashMap<String, Object> map) {

        log.info("map value is {}", map);
        Page page = TyPageUtil.getPage(map);

        QueryWrapper<TyUserLoginEntity> wrapper = new QueryWrapper();
        String keyword = null;
        if (map.containsKey("keyword")) {
            keyword = (String) map.get("keyword");
            if (StringUtil.isNumber(keyword)) {
                wrapper.lambda().eq(TyUserLoginEntity::getMemberId, Long.valueOf(keyword)).or().eq(TyUserLoginEntity::getMemberNickName, keyword);
            } else {
                wrapper.lambda().eq(TyUserLoginEntity::getMemberNickName, keyword);
            }
        }

        int status = 0;
        if (map.containsKey("status")) {
            status = Integer.valueOf(map.get("status").toString());
            wrapper.lambda().eq(TyUserLoginEntity::getStatus, status);
        }
        wrapper.eq("type", 1);
        return userLoginService.pageMaps(page, wrapper);
    }

//    private void validatePageParams(Integer pageIndex, Integer pageSize) {
//        if (null == pageIndex) {
//            throw new ValidateException(10000001, "分页参数不能为空。", "pageIndex");
//        } else if (null == pageSize) {
//            throw new ValidateException(10000002, "分页参数不能为空。", "pageSize");
//        }
//    }

    public static void main(String[] args) {
        System.out.println(new Date().toString());
    }

}
