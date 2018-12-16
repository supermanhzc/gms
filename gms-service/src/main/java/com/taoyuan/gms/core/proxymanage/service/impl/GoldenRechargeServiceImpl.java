package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.GoldenRechargeMapper;
import com.taoyuan.gms.core.proxymanage.service.IGoldenRechargeService;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import org.springframework.stereotype.Service;

@Service
public class GoldenRechargeServiceImpl extends ServiceImpl<GoldenRechargeMapper, GoldenRechargeEntity> implements IGoldenRechargeService {
    @Override
    public GoldenRechargeEntity getById(Long id) {
        QueryWrapper<GoldenRechargeEntity> wrapper = new QueryWrapper<GoldenRechargeEntity>();
        wrapper.lambda().eq(GoldenRechargeEntity::getId, id);
        return getOne(wrapper);
    }
}
