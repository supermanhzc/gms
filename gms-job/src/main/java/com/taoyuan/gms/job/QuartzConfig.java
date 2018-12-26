package com.taoyuan.gms.job;

import com.taoyuan.gms.job.adminmanage.ChartRewardJob;
import com.taoyuan.gms.job.adminmanage.LossRabateJob;
import com.taoyuan.gms.job.adminmanage.VChartRewardJob;
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
        log.info("定时任务测试");
        return JobBuilder.newJob(LossRabateJob.class).withIdentity("dailyLossRabateJobTetail").storeDurably().build();
    }

    @Bean
    public Trigger dailyLossRabateJobTrigger() {
        log.info("定时任务测试");
        return TriggerBuilder.newTrigger().forJob(dailyLossRabateJobTetail())
                .withIdentity("dailyLossRabateJobTetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }

    @Bean
    public JobDetail chartReawrdJobDetail() {
        log.info("定时任务测试");
        return JobBuilder.newJob(ChartRewardJob.class).withIdentity("chartReawrdJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger chartReawrdJobTrigger() {
        log.info("定时任务测试");
        return TriggerBuilder.newTrigger().forJob(chartReawrdJobDetail())
                .withIdentity("chartReawrdJobDetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }

    @Bean
    public JobDetail vChartReawrdJobDetail() {
        log.info("定时任务测试");
        return JobBuilder.newJob(VChartRewardJob.class).withIdentity("vChartReawrdJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger vChartReawrdJobTrigger() {
        log.info("定时任务测试");
        return TriggerBuilder.newTrigger().forJob(vChartReawrdJobDetail())
                .withIdentity("vChartReawrdJobDetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }
}
