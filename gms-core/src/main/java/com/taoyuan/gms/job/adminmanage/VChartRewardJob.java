package com.taoyuan.gms.job.adminmanage;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 虚拟排行奖统计定时任务，每天0点执行
 */
@Slf4j
public class VChartRewardJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO
        log.info("VChartRewardJob开始");
        log.info("VChartRewardJob结束");
    }
}
