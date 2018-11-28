package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.admin.prize.PrizeClassifyEntity;

public interface IPrizeClassifyService extends IService<PrizeClassifyEntity> {
    PrizeClassifyEntity getById(Long id);
}
