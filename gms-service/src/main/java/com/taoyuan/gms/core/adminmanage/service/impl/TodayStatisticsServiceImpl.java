package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.TodayStatisticsMapper;
import com.taoyuan.gms.core.adminmanage.service.ITodayStatisicsService;
import com.taoyuan.gms.model.entity.statistic.TodayStatisticsEntity;
import org.springframework.stereotype.Service;
@Service
public class TodayStatisticsServiceImpl extends ServiceImpl<TodayStatisticsMapper, TodayStatisticsEntity> implements ITodayStatisicsService {

}
