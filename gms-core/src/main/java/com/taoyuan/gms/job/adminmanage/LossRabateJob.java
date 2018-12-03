package com.taoyuan.gms.job.adminmanage;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 亏损统计定时任务
 */
@Slf4j
public class LossRabateJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO
        log.info("LossRabateJob计算每日亏损返利开始");
        log.info("LossRabateJob计算每日亏损返利结束");
    }
}
