package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.ExchargeCardPwdMapper;
import com.taoyuan.gms.core.adminmanage.service.IExchargeCardPwdService;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeCardPwdEntity;
import org.springframework.stereotype.Service;

@Service
public class ExchargeCardPwdServiceImpl extends ServiceImpl<ExchargeCardPwdMapper, ExchargeCardPwdEntity> implements IExchargeCardPwdService {
    @Override
    public ExchargeCardPwdEntity getById(Long id) {
        QueryWrapper<ExchargeCardPwdEntity> wrapper = new QueryWrapper<ExchargeCardPwdEntity>();
        wrapper.lambda().eq(ExchargeCardPwdEntity::getId, id);
        return getOne(wrapper);
    }

    @Override
    public ExchargeCardPwdEntity getByOrderId(int id) {
        QueryWrapper<ExchargeCardPwdEntity> wrapper = new QueryWrapper<ExchargeCardPwdEntity>();
        wrapper.lambda().eq(ExchargeCardPwdEntity::getOrderId, id);
        return getOne(wrapper);
    }
}
