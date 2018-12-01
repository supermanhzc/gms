package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.entity.TyProxyOperation;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyDateUtils;
import com.taoyuan.framework.oper.IProxyOperService;
import com.taoyuan.gms.api.proxy.GoldenRechargeApi;
import com.taoyuan.gms.core.adminmanage.controller.BaseController;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.job.JobManager;
import com.taoyuan.gms.job.proxymanage.GoldenRechargeJob;
import com.taoyuan.gms.model.entity.admin.ProxyOperEntity;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class GoldenRechargeController extends BaseController implements GoldenRechargeApi {

    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Autowired
    private TyUserService userService;

    @Autowired
    private IProxyOperService proxyOperService;
    private QueryWrapper wrapper;

    @Override
    public TyResponse createGoldenRecharge(GoldenRechargeEntity entity) {
        Long memberId = entity.getMemberId();
        TyUser user = userService.getUserById(memberId);
        if (null == user) {
            throw new ValidateException("会员不存在。");
        }

        Date date = new Date();
        entity.setTime(date);
        entity.setStatus(1);
        entity.setProxyId(TySession.getCurrentUser().getUserId());
        entity.setProxyName(TySession.getCurrentUser().getName());
        log.info("创建时间：{}", date);
        goldenRechargeService.save(entity);
        log.info("GoldenRechargeEntity id：{}", entity.getId());

        Long id = entity.getId();
        String cron = TyDateUtils.getCronAfterMinutes(5);
        JobManager.addJob("GoldRecharge" + id, GoldenRechargeJob.class, cron, id);

        //保存代理操作记录s
        TyProxyOperation operEntity = new TyProxyOperation();
        //代充
        operEntity.setType(1);
        operEntity.setAccount(BigDecimal.valueOf(10000));
        operEntity.setDescription("金币代充");
        operEntity.setMoneyChanged(BigDecimal.valueOf(0 - entity.getAmount()));
        operEntity.setProxyId(TySession.getCurrentUser().getUserId());
        operEntity.setProxyName(TySession.getCurrentUser().getName());
        operEntity.setTime(date);
        proxyOperService.save(operEntity);

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
    public IPage<Map<String, Object>> retrieve(Map<String, Object> map) {
        Page page = getPageWithoutValidation(map);
        return goldenRechargeService.pageMaps(page, new QueryWrapper<GoldenRechargeEntity>());
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
