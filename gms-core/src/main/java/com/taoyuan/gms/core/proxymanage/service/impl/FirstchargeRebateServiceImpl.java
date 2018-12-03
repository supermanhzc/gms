package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.bs.service.TyBaseServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.FirstchargeRebateMapper;
import com.taoyuan.gms.core.proxymanage.service.IFirstchargeRebateService;
import com.taoyuan.gms.model.entity.proxy.FirstchargeRebateEntity;
import org.springframework.stereotype.Service;

@Service
public class FirstchargeRebateServiceImpl extends TyBaseServiceImpl<FirstchargeRebateMapper, FirstchargeRebateEntity> implements IFirstchargeRebateService {
    @Override
    public boolean exist(Long id) {
        return null != getOne(new QueryWrapper<FirstchargeRebateEntity>().lambda().eq(FirstchargeRebateEntity::getMemberId, id));
    }
}
