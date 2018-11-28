package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.RechargeWithdrawMapper;
import com.taoyuan.gms.core.proxymanage.service.IRechargeWithdrawService;
import com.taoyuan.gms.model.entity.proxy.RechargeWithdrawEntity;
import org.springframework.stereotype.Service;

@Service
public class RechargeWithdrawServiceImpl extends ServiceImpl<RechargeWithdrawMapper, RechargeWithdrawEntity> implements IRechargeWithdrawService {
    @Override
    public RechargeWithdrawEntity getOne(Long id) {
        return getOne(new QueryWrapper<RechargeWithdrawEntity>().lambda().eq(RechargeWithdrawEntity::getId, id));
    }
}
