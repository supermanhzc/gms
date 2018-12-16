package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.PrizeClassifyMapper;
import com.taoyuan.gms.core.adminmanage.service.IPrizeClassifyService;
import com.taoyuan.gms.model.entity.admin.prize.PrizeClassifyEntity;
import org.springframework.stereotype.Service;

@Service
public class PrizeClassifyServiceImpl extends ServiceImpl<PrizeClassifyMapper, PrizeClassifyEntity> implements IPrizeClassifyService {
    @Override
    public PrizeClassifyEntity getById(Long id) {
        return getOne(new QueryWrapper<PrizeClassifyEntity>().lambda().eq(PrizeClassifyEntity::getId,id));
    }
}
