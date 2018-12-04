package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyDateUtils;
import com.taoyuan.gms.api.proxy.GoldenRechargeApi;
import com.taoyuan.gms.core.proxymanage.dao.GoldenRechargeMapper;
import com.taoyuan.gms.core.proxymanage.service.IFirstchargeRebateService;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.core.proxymanage.service.IProxyOperService;
import com.taoyuan.gms.core.sitemanage.account.service.IGoldService;
import com.taoyuan.gms.job.JobManager;
import com.taoyuan.gms.job.proxymanage.GoldenRechargeJob;
import com.taoyuan.gms.model.entity.proxy.FirstchargeRebateEntity;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@RestController
public class GoldenRechargeController extends BaseGmsProxyController implements GoldenRechargeApi {

    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Autowired
    private GoldenRechargeMapper goldenRechargeMapper;

    @Autowired
    private TyUserService userService;

    @Autowired
    private IProxyOperService proxyOperService;

    @Autowired
    private IFirstchargeRebateService firstchargeRebateService;

    @Autowired
    private IGoldService goldService;

    @Override
    public TyResponse createGoldenRecharge(GoldenRechargeEntity entity) {
        Long memberId = entity.getMemberId();
        if (null == memberId) {
            throw new ValidateException("会员ID不能为空。");
        }

        TyUser user = userService.getUserById(memberId);
        if (null == user) {
            throw new ValidateException("会员不存在。");
        }

        BigDecimal account = entity.getAmount();
        if (account.compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidateException("代充金额不能为0。");
        }

        //代充扣除额度
        BigDecimal discountMoney =
                account.multiply(BigDecimal.valueOf(getWebSetting().getProxyRechargeDiscount())).divide(BigDecimal.valueOf(100));
        //判断代理余额是否够代充，计算逻辑，用户代充额度*代充折扣 ≤ 代理余额
        BigDecimal proxyBalance = getBalance(getCurrentUserId());
        if (proxyBalance.compareTo(discountMoney) < 0) {
            throw new ValidateException("代理余额不足，请充值。");
        }

        Date date = new Date();
        entity.setTime(date);
        entity.setStatus(1);
        entity.setProxyId(getCurrentUserId());
        entity.setProxyName(getCurrentUserName());
        log.info("创建时间：{}", date);
        goldenRechargeService.save(entity);
        log.info("GoldenRechargeEntity id：{}", entity.getId());


        Long id = entity.getId();
        String cron = TyDateUtils.getCronAfterMinutes(5);
        JobManager.addJob("GoldRecharge" + id, GoldenRechargeJob.class, cron, id);

        recordOperation(1, "金币代充", discountMoney);

        BigDecimal totalGold = account.multiply(BigDecimal.valueOf(getWebSetting().getExchangePropor()));
        //判断是否是首充，如果是则记录首充奖励信息
        if (!firstchargeRebateService.exist(memberId)) {
            FirstchargeRebateEntity firstchargeRebateEntity = new FirstchargeRebateEntity();
            firstchargeRebateEntity.setDate(date);
            firstchargeRebateEntity.setMemberId(memberId);
            firstchargeRebateEntity.setMemberName(user.getName());
            //首充返利金币
            BigDecimal firstRebateGole = account.multiply(BigDecimal.valueOf(getWebSetting().getFirstFillRebatePropor())).divide(BigDecimal.valueOf(100));
            firstchargeRebateEntity.setRebate(firstRebateGole);
            //增加返利金币给用户
            totalGold = totalGold.add(firstRebateGole);
            firstchargeRebateEntity.setYtdFirstcharge(account);
            firstchargeRebateEntity.setStatus(1);
            firstchargeRebateService.save(firstchargeRebateEntity);
        }

        //更改会员金币数量
        goldService.addGold(memberId, totalGold);
        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse get5GoldenRecharge() {
        Page page = new Page(1, 5);
        QueryWrapper<GoldenRechargeEntity> wrapper = new QueryWrapper<GoldenRechargeEntity>();
        wrapper.orderByDesc("time");
        return new TySuccessResponse(goldenRechargeService.page(page, wrapper));
    }

    @Override
    public TyResponse withdraw(GoldenRechargeEntity entity) {
        if (null == entity) {
            throw new ValidateException("对象不能为空。");
        }

        Long id = entity.getId();
        if (null == id) {
            throw new ValidateException("id不能为空");
        }

        GoldenRechargeEntity dbValue = goldenRechargeService.getById(id);
        if (1 != dbValue.getStatus()) {
            throw new ValidateException("不能撤销此对象。");
        }

        //设置为不可撤销
        dbValue.setStatus(2);
        //更新状态为“已撤销0”
        dbValue.setWithdraw(0);
        goldenRechargeService.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse retrieve(TyPageEntity pageEntity) {
        Page page = getPage(pageEntity);
        return new TySuccessResponse(goldenRechargeMapper.selectPage(page, new QueryWrapper()));
    }

}
