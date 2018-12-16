package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.LevelSettingMapper;
import com.taoyuan.gms.core.adminmanage.service.ILevelSettingService;
import com.taoyuan.gms.model.entity.admin.web.LevelSettingEntity;
import org.springframework.stereotype.Service;

@Service
public class LevelSettingServiceImpl extends ServiceImpl<LevelSettingMapper,LevelSettingEntity> implements ILevelSettingService {
}
