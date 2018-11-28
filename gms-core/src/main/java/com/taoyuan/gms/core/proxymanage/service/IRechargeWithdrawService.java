package com.taoyuan.gms.core.proxymanage.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.proxy.RechargeWithdrawEntity;

public interface IRechargeWithdrawService extends IService<RechargeWithdrawEntity> {

    RechargeWithdrawEntity getOne(Long id);
}
