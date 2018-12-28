package com.taoyuan.gms.job.proxymanage;

import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.util.TyDateUtils;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.core.proxymanage.service.ICardPwdWithdrawService;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.core.proxymanage.service.IProxySaleStatisticService;
import com.taoyuan.gms.model.entity.proxy.ProxySaleStatisticEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Configuration
public class ProxySaleStatisticJob extends QuartzJobBean {

    private static ProxySaleStatisticJob job;

    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Autowired
    private ICardPasswordService cardPasswordService;

    @Autowired
    private ICardPwdWithdrawService cardPwdWithdrawService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProxySaleStatisticService proxySaleStatisticService;

    @PostConstruct
    public void init() {
        job = this;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("计算代理每日统计开始");
        List<TyUser> proxys = userService.queryAllProxys();
        if(CollectionUtils.isEmpty(proxys)){
            return;
        }

        List<ProxySaleStatisticEntity> proxySaleStatisticEntityList = new ArrayList<ProxySaleStatisticEntity>();
        for(TyUser user: proxys){
            Long id = user.getId();
//            Date start = TyDateUtils.getYesterdayStartTime();
            Date start = new Date();
//            Date end = TyDateUtils.getYesterdayEndTime();
            Date end = new Date();
            ProxySaleStatisticEntity entity = new ProxySaleStatisticEntity();
//            entity.setDate(TyDateUtils.getYesterdayDate());
            entity.setDate(LocalDate.now());
            entity.setCardPwd(cardPasswordService.getStatistic(id,start,end));
            entity.setRecharge(goldenRechargeService.getStatistic(id,start,end));
            entity.setWithdraw(cardPwdWithdrawService.getStatistic(id,start,end));
            proxySaleStatisticEntityList.add(entity);
        }
        proxySaleStatisticService.saveBatch(proxySaleStatisticEntityList);
        log.info("计算代理每日统计结束");
    }
}
