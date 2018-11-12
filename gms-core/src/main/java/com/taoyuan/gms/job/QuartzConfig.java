package com.taoyuan.gms.job;

import com.taoyuan.gms.job.adminmanage.LossRabateJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务配置
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail dailyLossRabateJobTetail() {
        return JobBuilder.newJob(LossRabateJob.class).withIdentity("dailyLossRabateJobTetail").storeDurably().build();
    }

    @Bean
    public Trigger dailyLossRabateJobTrigger() {
        return TriggerBuilder.newTrigger().forJob(dailyLossRabateJobTetail())
                .withIdentity("dailyLossRabateJobTetail")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 0 * * ?"))
                .build();
    }
}
