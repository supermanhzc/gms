package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.framework.common.util.TyDateUtils;
import com.taoyuan.gms.core.proxymanage.dao.GoldenRechargeMapper;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GoldenRechargeServiceImpl
    extends ServiceImpl<GoldenRechargeMapper, GoldenRechargeEntity>
    implements IGoldenRechargeService
{
    @Override
    public GoldenRechargeEntity getById(Long id)
    {
        QueryWrapper<GoldenRechargeEntity> wrapper =
            new QueryWrapper<GoldenRechargeEntity>();
        wrapper.lambda().eq(GoldenRechargeEntity::getId, id);
        return getOne(wrapper);
    }
    
    @Override
    public BigDecimal getStatistic(Long id, Date start, Date end)
    {
        QueryWrapper<GoldenRechargeEntity> wrapper =
            new QueryWrapper<GoldenRechargeEntity>();
        wrapper.lambda()
            .eq(GoldenRechargeEntity::getProxyId, id)
            .eq(GoldenRechargeEntity::getStatus, 2)
            .between(GoldenRechargeEntity::getTime, start, end);
        List<GoldenRechargeEntity> goldenRechargeEntityList = list(wrapper);
        BigDecimal statistic = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(goldenRechargeEntityList))
        {
            for (GoldenRechargeEntity entity : goldenRechargeEntityList)
            {
                statistic.add(entity.getAmount());
            }
        }
        
        return statistic;
    }
}
