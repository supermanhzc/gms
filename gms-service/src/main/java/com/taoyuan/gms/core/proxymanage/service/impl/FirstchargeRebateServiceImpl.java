package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.bs.service.TyBaseServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.FirstchargeRebateMapper;
import com.taoyuan.gms.core.proxymanage.service.IFirstchargeRebateService;
import com.taoyuan.gms.model.entity.proxy.FirstchargeRebateEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FirstchargeRebateServiceImpl extends TyBaseServiceImpl<FirstchargeRebateMapper, FirstchargeRebateEntity> implements IFirstchargeRebateService {
    @Override
    public boolean exist(Long id, Date date) {
        QueryWrapper<FirstchargeRebateEntity> wrapper = new QueryWrapper<FirstchargeRebateEntity>();
        wrapper.lambda().eq(FirstchargeRebateEntity::getMemberId, id).eq(FirstchargeRebateEntity::getDate, date);
        return null != getOne(wrapper);
    }
}
