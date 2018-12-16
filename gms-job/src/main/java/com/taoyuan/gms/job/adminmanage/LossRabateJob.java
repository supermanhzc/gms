package com.taoyuan.gms.job.adminmanage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.taoyuan.gms.core.adminmanage.dao.UserMapper;
import com.taoyuan.gms.core.adminmanage.service.ILossRabateService;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.core.adminmanage.service.IWebSettingService;
import com.taoyuan.gms.model.entity.admin.LossRabateEntity;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 亏损统计定时任务
 */
@Slf4j
public class LossRabateJob extends QuartzJobBean {

    private static LossRabateJob lossRabateJob;

    @Autowired
    private ILossRabateService lossRabateService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IWebSettingService webSettingService;

    @PostConstruct
    public void init() {
        lossRabateJob = this;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("LossRabateJob计算每日亏损返利开始");
        //将所有未领取的全部更新为已逾期3
        UpdateWrapper<LossRabateEntity> updateWrapper = new UpdateWrapper<LossRabateEntity>();
        updateWrapper.lambda().eq(LossRabateEntity::getStatus,1);
        LossRabateEntity updateEntity = new LossRabateEntity();
        updateEntity.setStatus(3);
        lossRabateService.update(updateEntity,updateWrapper);

        List<LossRabateEntity> lossRabateEntityList = new ArrayList<LossRabateEntity>();
        List<UserEntity> userList = userService.list(null);
        for (UserEntity userEntity : userList) {
            Long id = userEntity.getId();
            String nickName = userEntity.getNickName();
            BigDecimal balance = userEntity.getBalance();

            LocalDate date = LocalDate.now();
            LossRabateEntity lossRabateEntity = new LossRabateEntity();
            lossRabateEntity.setMemberId(id);
            lossRabateEntity.setMemberNickName(nickName);
            //TODO 计算亏损
            BigDecimal loss = getLoss();
            lossRabateEntity.setLoss(loss);
            lossRabateEntity.setRabate(loss.multiply(BigDecimal.valueOf(getLossRebatePropor())));
            //默认未领取
            lossRabateEntity.setStatus(1);
            lossRabateEntity.setTime(date);
            lossRabateEntityList.add(lossRabateEntity);
        }

        lossRabateService.saveBatch(lossRabateEntityList);
        log.info("LossRabateJob计算每日亏损返利结束");
    }

    private BigDecimal getLoss(){
        return BigDecimal.valueOf(1000);
    }

    private Double getLossRebatePropor(){
        return webSettingService.getOne(null).getLossRebatePropor();
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalDate ystdDate = localDate.minusDays(1);
        System.out.println(ystdDate);
    }

    public LossRabateEntity getYstdLossRabate(Long id, LocalDate date) {
        QueryWrapper<LossRabateEntity> wrapper = new QueryWrapper<LossRabateEntity>();
        wrapper.lambda().eq(LossRabateEntity::getMemberId, id).eq(LossRabateEntity::getTime, date);
        return lossRabateService.getOne(wrapper);
    }
}
