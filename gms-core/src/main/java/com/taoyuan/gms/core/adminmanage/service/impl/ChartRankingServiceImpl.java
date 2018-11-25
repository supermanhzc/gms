package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.ChartRankingMapper;
import com.taoyuan.gms.core.adminmanage.service.IChartRankingService;
import com.taoyuan.gms.model.entity.admin.web.ChartRankingEntity;
import org.springframework.stereotype.Service;

@Service
public class ChartRankingServiceImpl extends ServiceImpl<ChartRankingMapper, ChartRankingEntity> implements IChartRankingService {
}
