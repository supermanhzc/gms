package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.GameSettingMapper;
import com.taoyuan.gms.core.adminmanage.service.IGameSettingService;
import com.taoyuan.gms.model.entity.admin.web.GameSettingEntity;
import org.springframework.stereotype.Service;

@Service
public class GameSettingServiceImpl extends ServiceImpl<GameSettingMapper, GameSettingEntity> implements IGameSettingService {
    @Override
    public GameSettingEntity getByName(String name) {
        return getOne(new QueryWrapper<GameSettingEntity>().lambda().eq(GameSettingEntity::getGameName,name));
    }
}
