package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.ExchargeOrderMapper;
import com.taoyuan.gms.core.adminmanage.service.IExchargeOrderService;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeOrderEntity;
import org.springframework.stereotype.Service;

@Service
public class ExchargeOrderServiceImpl extends ServiceImpl<ExchargeOrderMapper, ExchargeOrderEntity> implements IExchargeOrderService {
    @Override
    public ExchargeOrderEntity getById(Long id) {
        QueryWrapper<ExchargeOrderEntity> wrapper = new QueryWrapper<ExchargeOrderEntity>();
        wrapper.lambda().eq(ExchargeOrderEntity::getId, id);
        return getOne(wrapper);
    }

    @Override
    public ExchargeOrderEntity getByOrderId(int id) {
        QueryWrapper<ExchargeOrderEntity> wrapper = new QueryWrapper<ExchargeOrderEntity>();
        wrapper.lambda().eq(ExchargeOrderEntity::getOrderId, id);
        return getOne(wrapper);
    }
}
