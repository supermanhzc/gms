package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.admin.web.GameSettingEntity;

public interface IGameSettingService extends IService<GameSettingEntity> {

    GameSettingEntity getByName(String name);
}
