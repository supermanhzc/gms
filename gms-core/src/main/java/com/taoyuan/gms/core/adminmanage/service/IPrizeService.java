package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.admin.prize.PrizeEntity;

public interface IPrizeService extends IService<PrizeEntity> {
    PrizeEntity getById(Long id);
}
