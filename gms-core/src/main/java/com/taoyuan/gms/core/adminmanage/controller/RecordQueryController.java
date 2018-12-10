package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyBigNumUtil;
import com.taoyuan.framework.mail.TyVerificationCodeMapper;
import com.taoyuan.framework.mail.TyVerificationCodeService;
import com.taoyuan.gms.api.admin.RecordsQueryApi;
import com.taoyuan.gms.common.util.StringUtil;
import com.taoyuan.gms.core.adminmanage.dao.*;
import com.taoyuan.gms.core.adminmanage.service.*;
import com.taoyuan.gms.core.proxymanage.dao.GoldenRechargeMapper;
import com.taoyuan.gms.core.proxymanage.dao.ProxyOperMapper;
import com.taoyuan.gms.core.proxymanage.service.IFirstchargeRebateService;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.core.proxymanage.service.IProxyOperService;
import com.taoyuan.gms.model.dto.admin.charts.ChartsRewardPageRequest;
import com.taoyuan.gms.model.dto.admin.charts.VChartsRewardPageRequest;
import com.taoyuan.gms.model.dto.admin.chipin.ChipinWagePageRequest;
import com.taoyuan.gms.model.dto.admin.lossrebate.LossRebateRequest;
import com.taoyuan.gms.model.dto.admin.statistic.*;
import com.taoyuan.gms.model.entity.admin.JuniorCommissionEntity;
import com.taoyuan.gms.model.entity.admin.LossRabateEntity;
import com.taoyuan.gms.model.entity.admin.UserLoginEntity;
import com.taoyuan.gms.model.entity.admin.charts.ChartsRewardsEntity;
import com.taoyuan.gms.model.entity.admin.chipin.ChipinWageEntity;
import com.taoyuan.gms.model.entity.admin.records.SaleDetailEntity;
import com.taoyuan.gms.model.entity.proxy.FirstchargeRebateEntity;
import com.taoyuan.gms.model.entity.statistic.TodayStatisticsEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@Slf4j
@RestController
public class RecordQueryController extends BaseGmsController implements RecordsQueryApi {

    @Autowired
    private TyVerificationCodeService verificationCodeService;

    @Autowired
    private TyVerificationCodeMapper verificationCodeMapper;

    @Autowired
    private IChartsRewardsService chartsRewardsService;

    @Autowired
    private ChartsRewardsMapper chartsRewardsMapper;

    @Autowired
    private ILossRabateService lossRabateService;

    @Autowired
    private LossRabateMapper lossRabateMapper;

    @Autowired
    private IProxyOperService proxyOperService;

    @Autowired
    private ProxyOperMapper proxyOperMapper;

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private ISaleDetailService saleDetailService;

    @Autowired
    private SaleDetailMapper saleDetailMapper;

    @Autowired
    private IChipinWageService chipinWageService;

    @Autowired
    private ChipinWageMapper chipinWageMapper;

    @Autowired
    private IJuniorCommissionService juniorCommissionService;

    @Autowired
    private JuniorCommissionMapper juniorCommissionMapper;

    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Autowired
    private GoldenRechargeMapper goldenRechargeMapper;

    @Autowired
    private ITodayStatisicsService todayStatisticsService;

    @Autowired
    private IFirstchargeRebateService firstchargeRebateService;

    @Override
    public TyResponse getVerificationCodes(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        return new TySuccessResponse(verificationCodeMapper.selectPage(page, null));
    }

    @Override
    public TyResponse getRecharges(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        return new TySuccessResponse(goldenRechargeMapper.selectPage(page, null));
    }

    @Override
    public TyResponse getProxyOperates(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        return new TySuccessResponse(proxyOperMapper.selectPage(page, null));
    }

    @Override
    public TyResponse getSaleStatistics(@RequestBody SaleStatisticsRequest request) {
        Page page = getPage(request);
        QueryWrapper<SaleDetailEntity> wrapper = new QueryWrapper<SaleDetailEntity>();
        String begin = request.getStart();
        String end = request.getEnd();
        if (!StringUtils.isEmpty(begin)) {
            if (!StringUtils.isEmpty(end)) {
                wrapper.between("time", begin, end);
            } else {
                throw new ValidateException(10000010, "请选择结束时间。", null);
            }
        } else {
            if (!StringUtils.isEmpty(end)) {
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
        return new TySuccessResponse(rsltList);
    }

    @Override
    public TyResponse getSaleDetails(@RequestBody SaleStatisticsRequest request) {
        Page page = getPage(request);

        QueryWrapper<SaleDetailEntity> wrapper = new QueryWrapper<SaleDetailEntity>();
        String proxyName = request.getProxyName();
        if (!StringUtils.isEmpty(proxyName)) {
            wrapper.eq("proxyName", proxyName);
        }

        String begin = request.getStart();
        String end = request.getEnd();
        if (!StringUtils.isEmpty(begin)) {
            if (!StringUtils.isEmpty(end)) {
                wrapper.between("time", begin, end);
            } else {
                throw new ValidateException(10000010, "请选择结束时间。", null);
            }
        } else {
            if (!StringUtils.isEmpty(end)) {
                throw new ValidateException(10000010, "请选择开始时间。", null);
            }
        }

        return new TySuccessResponse(saleDetailMapper.selectPage(page, wrapper));
    }

    @Override
    public TyResponse getLossRebates(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        return new TySuccessResponse(lossRabateMapper.selectPage(page, null));
    }

    @Override
    public TyResponse getLossRebates(@RequestBody LossRebateRequest request) {
        Page page = getPage(request);
        QueryWrapper<LossRabateEntity> wrapper = new QueryWrapper<LossRabateEntity>();
        Long id = request.getId();
        int status = request.getStatus();
        if (null != id) {
            wrapper.lambda().eq(LossRabateEntity::getMemberId, id);
            if (0 != status) {
                wrapper.lambda().eq(LossRabateEntity::getStatus, status);
            }
        } else {
            if (0 != status) {
                wrapper.lambda().eq(LossRabateEntity::getStatus, status);
            }
        }

        return new TySuccessResponse(lossRabateMapper.selectPage(page, wrapper));
    }

    @Override
    public TyResponse getChartsRewards(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        List<ChartsRewardsEntity> list = new ArrayList<ChartsRewardsEntity>();
        QueryWrapper<ChartsRewardsEntity> wrapper = new QueryWrapper<ChartsRewardsEntity>();
        wrapper.eq("type", 1);
        return new TySuccessResponse(chartsRewardsMapper.selectPage(new Page<ChartsRewardsEntity>(pageIndex,
                pageSize), wrapper));
    }

    @Override
    public TyResponse getChartsRewards(@RequestBody ChartsRewardPageRequest request) {
        Page page = getPage(request);

        QueryWrapper<ChartsRewardsEntity> wrapper = new QueryWrapper<ChartsRewardsEntity>();
        wrapper.lambda().eq(ChartsRewardsEntity::getType, 1);
        Long id = request.getId();
        int status = request.getStatus();
        if (null != id) {
            wrapper.lambda().eq(ChartsRewardsEntity::getMemberId, id);
        }

        if (0 != status) {
            wrapper.lambda().eq(ChartsRewardsEntity::getStatus, status);
        }

        return new TySuccessResponse(chartsRewardsMapper.selectPage(page, wrapper));
    }

    @Override
    public TyResponse getVChartsRewards(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        QueryWrapper<ChartsRewardsEntity> wrapper = new QueryWrapper<ChartsRewardsEntity>();
        wrapper.eq("type", 2);
        return new TySuccessResponse(chartsRewardsMapper.selectPage(page, wrapper));
    }

    @Override
    public TyResponse getVChartsRewards(@RequestBody VChartsRewardPageRequest request) {
        Page page = getPage(request);

        QueryWrapper<ChartsRewardsEntity> wrapper = new QueryWrapper<ChartsRewardsEntity>();
        wrapper.lambda().eq(ChartsRewardsEntity::getType, 2);
        Long id = request.getId();
        int status = request.getStatus();
        if (null != id) {
            wrapper.lambda().eq(ChartsRewardsEntity::getMemberId, id);
        }

        if (0 != status) {
            wrapper.lambda().eq(ChartsRewardsEntity::getStatus, status);
        }

        return new TySuccessResponse(chartsRewardsMapper.selectPage(page, wrapper));
    }


    @Override
    public TyResponse getChipinWages(@RequestBody ChipinWagePageRequest request) {
        Page page = getPage(request);
        QueryWrapper<ChipinWageEntity> wrapper = new QueryWrapper<ChipinWageEntity>();
        Long id = request.getId();
        int status = request.getStatus();
        if (null != id) {
            wrapper.lambda().eq(ChipinWageEntity::getMemberId, id);
        }

        if (0 != status) {
            wrapper.lambda().eq(ChipinWageEntity::getStatus, status);
        }

        return new TySuccessResponse(chipinWageMapper.selectPage(page, wrapper));
    }

    @Override
    public TyResponse getJuniorCommissions(@RequestBody JuniorCommissionsPageRequest request) {
        Page page = getPage(request);
        QueryWrapper<JuniorCommissionEntity> wrapper = new QueryWrapper();
        Long id = request.getId();
        if (null != id) {
            wrapper.lambda().eq(JuniorCommissionEntity::getMemberId, id);
        }

        int status = request.getStatus();
        if (0 != status) {
            wrapper.lambda().eq(JuniorCommissionEntity::getStatus, status);
        }

        return new TySuccessResponse(juniorCommissionMapper.selectPage(page, wrapper));
    }

    @Override
    public TyResponse getFirstchargeRebates() {
        QueryWrapper<FirstchargeRebateEntity> wrapper = new QueryWrapper<FirstchargeRebateEntity>();
        wrapper.lambda().orderByDesc(FirstchargeRebateEntity::getDate);
        return new TySuccessResponse(firstchargeRebateService.list(wrapper));
    }

    @Override
    public TyResponse getFirstchargeRebates(@RequestBody FirstchargeRebateRequest request) {
        QueryWrapper<FirstchargeRebateEntity> wrapper = new QueryWrapper<FirstchargeRebateEntity>();
        wrapper.lambda().orderByDesc(FirstchargeRebateEntity::getDate);
        Long id = request.getId();
        if (null != id) {
            wrapper.lambda().eq(FirstchargeRebateEntity::getMemberId, id);
        }

        int status = request.getStatus();
        if (0 != status) {
            wrapper.lambda().eq(FirstchargeRebateEntity::getStatus, status);
        }
        return new TySuccessResponse(firstchargeRebateService.list(wrapper));
    }

    @Override
    public TyResponse getDailyStatistic() {
        //TODO需要从各个表实时查询，只查询当日
        DailyStatisticResponse dto = new DailyStatisticResponse();

        QueryWrapper<TodayStatisticsEntity> wrapper = new QueryWrapper<TodayStatisticsEntity>();
        TodayStatisticsEntity todayStatisticsEntity = todayStatisticsService.getOne(wrapper);
        dto.setDate(todayStatisticsEntity.getToday());
        dto.setRegisterMemberNum(todayStatisticsEntity.getUserCount());
        dto.setMembersBalance(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getUserBalance()));
        dto.setProxyBalance(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getProxyBalance()));
        dto.setGameProfitLoss(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getGameBalance()));
        dto.setExchange(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getCashBalance()));
        dto.setSubstitute(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getRechargBalance()));
        dto.setExchangePoundage(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getCashCost()));
        dto.setCdKeyRecharge(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getCardBalance()));
        dto.setChartsReward(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getRankingBalance()));
        dto.setChipinCommission(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getBettingCommission()));
        dto.setFirstChargeRebate(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getFirstReward()));
        dto.setLossRebate(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getLossRebate()));
        dto.setChipinWage(TyBigNumUtil.cvrtNum2String(todayStatisticsEntity.getBettingWage()));
        return new TySuccessResponse(dto);
    }

    @Override
    public TyResponse getAdminLogins(@RequestBody TyPageEntity pageEntity) {
        log.info("getAdminLogins map={}", pageEntity);
        Page page = getPage(pageEntity);

        QueryWrapper<UserLoginEntity> wrapper = new QueryWrapper<UserLoginEntity>();
        wrapper.eq("type", 3);
        return new TySuccessResponse(userLoginMapper.selectPage(page, wrapper));

    }

    @Override
    public TyResponse getMemberLogins(MemberLoginRequest request) {

        log.info("map value is {}", request);
        Page page = getPage(request);

        QueryWrapper<UserLoginEntity> wrapper = new QueryWrapper();
        String keyword = request.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            if (StringUtil.isNumber(keyword)) {
                wrapper.lambda().eq(UserLoginEntity::getMemberId, Long.valueOf(keyword)).or().eq(UserLoginEntity::getMemberNickName, keyword);
            } else {
                wrapper.lambda().eq(UserLoginEntity::getMemberNickName, keyword);
            }
        }

        int status = request.getStatus();
        if (0 != status) {
            wrapper.lambda().eq(UserLoginEntity::getStatus, status);
        }
        wrapper.eq("type", 1);
        return new TySuccessResponse(userLoginMapper.selectPage(page, wrapper));
    }

    public static void main(String[] args) {
        System.out.println(new Date().toString());
    }

}
