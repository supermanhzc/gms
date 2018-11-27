package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.PrizeMapper;
import com.taoyuan.gms.core.adminmanage.service.IPrizeService;
import com.taoyuan.gms.model.entity.admin.prize.PrizeEntity;
import org.springframework.stereotype.Service;

@Service
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, PrizeEntity> implements IPrizeService {
    @Override
    public PrizeEntity getById(Long id) {
        return null;
    }
}
