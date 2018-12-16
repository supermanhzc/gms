package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.ChartRankingSeetingMapper;
import com.taoyuan.gms.core.adminmanage.service.ICHartRankingSettingService;
import com.taoyuan.gms.model.entity.admin.web.ChartRankingSettingEntity;
import org.springframework.stereotype.Service;

@Service
public class ChartRankingSettingServiceImpl extends ServiceImpl<ChartRankingSeetingMapper, ChartRankingSettingEntity> implements ICHartRankingSettingService {
}
