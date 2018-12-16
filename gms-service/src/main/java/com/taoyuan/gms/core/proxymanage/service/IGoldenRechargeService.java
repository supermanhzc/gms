package com.taoyuan.gms.core.proxymanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;

public interface IGoldenRechargeService extends IService<GoldenRechargeEntity> {

    GoldenRechargeEntity getById(Long id);
}
