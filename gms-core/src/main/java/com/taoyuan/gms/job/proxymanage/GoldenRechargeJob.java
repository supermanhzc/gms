package com.taoyuan.gms.job.proxymanage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.job.JobManager;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class GoldenRechargeJob extends QuartzJobBean {
    @Autowired
    private IGoldenRechargeService goldenRechargeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取业务主键
        String id = jobExecutionContext.getJobDetail().getJobDataMap().getString("GoldenRecharge");

        GoldenRechargeEntity entity = getEntityById(id);
        //更新状态为不可撤销
        entity.setStatus(2);
        UpdateWrapper<GoldenRechargeEntity> wrapper = new UpdateWrapper<GoldenRechargeEntity>();
        wrapper.lambda().eq(GoldenRechargeEntity::getId,id);
        goldenRechargeService.update(entity,wrapper);

        //删除任务
        JobManager.removeJob("GoldenRecharge");
    }

    private GoldenRechargeEntity getEntityById(String id){
        QueryWrapper<GoldenRechargeEntity> queryWrapper = new QueryWrapper<GoldenRechargeEntity>();
        queryWrapper.lambda().eq(GoldenRechargeEntity::getId,Long.valueOf(id));
        return goldenRechargeService.getOne(queryWrapper);
    }
}
