package com.taoyuan.gms.job.adminmanage;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 投注工资定时任务，每日0点后记录工资记录
 * 有效流水额=自己前一天的有效流水统计
 * 工资额=有效流水额*投注工资比例
 */
@Slf4j
public class ChipinWageJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO
        log.info("ChipinWageJob开始");
        log.info("ChipinWageJob结束");
    }
}
