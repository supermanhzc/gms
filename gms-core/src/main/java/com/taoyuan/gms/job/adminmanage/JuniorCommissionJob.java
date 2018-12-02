package com.taoyuan.gms.job.adminmanage;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 下线提成计算定时任务每日0点后记录工资记录
 * 昨日下线总流水=自己的下线前一天的有效流水总和
 * 工资额=昨日下线总流水*下线投注工资比例
 */
@Slf4j
public class JuniorCommissionJob  extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO
        log.info("ChipinWageJob开始");
        log.info("ChipinWageJob结束");
    }
}