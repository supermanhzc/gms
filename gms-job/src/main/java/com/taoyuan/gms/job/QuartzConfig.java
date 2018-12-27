package com.taoyuan.gms.job;

import com.taoyuan.gms.job.adminmanage.ChartRewardJob;
import com.taoyuan.gms.job.adminmanage.LossRabateJob;
import com.taoyuan.gms.job.adminmanage.VChartRewardJob;
import com.taoyuan.gms.job.proxymanage.ProxySaleStatisticJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务配置
 */
@Slf4j
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail dailyLossRabateJobTetail() {
        log.info("日亏损返利定时任务");
        return JobBuilder.newJob(LossRabateJob.class).withIdentity("dailyLossRabateJobTetail").storeDurably().build();
    }

    @Bean
    public Trigger dailyLossRabateJobTrigger() {
        log.info("日亏损返利定时任务触发器");
        return TriggerBuilder.newTrigger().forJob(dailyLossRabateJobTetail())
                .withIdentity("dailyLossRabateJobTetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }

    @Bean
    public JobDetail chartReawrdJobDetail() {
        log.info("排行奖定时任务");
        return JobBuilder.newJob(ChartRewardJob.class).withIdentity("chartReawrdJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger chartReawrdJobTrigger() {
        log.info("排行奖定时任务触发器");
        return TriggerBuilder.newTrigger().forJob(chartReawrdJobDetail())
                .withIdentity("chartReawrdJobDetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }

    @Bean
    public JobDetail vChartReawrdJobDetail() {
        log.info("虚拟排行奖定时任务");
        return JobBuilder.newJob(VChartRewardJob.class).withIdentity("vChartReawrdJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger vChartReawrdJobTrigger() {
        log.info("虚拟排行奖定时任务触发器");
        return TriggerBuilder.newTrigger().forJob(vChartReawrdJobDetail())
                .withIdentity("vChartReawrdJobDetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }

    @Bean
    public JobDetail proxySaleStatisticJobDetail() {
        log.info("代理销售统计定时任务");
        return JobBuilder.newJob(ProxySaleStatisticJob.class).withIdentity("proxySaleStatisticJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger proxySaleStatisticJobTrigger() {
        log.info("代理销售统计定时任务触发器");
        return TriggerBuilder.newTrigger().forJob(vChartReawrdJobDetail())
                .withIdentity("proxySaleStatisticJobDetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }
}
