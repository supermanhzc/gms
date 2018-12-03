package com.taoyuan.gms.core.sitemanage.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.bs.service.TyBaseServiceImpl;
import com.taoyuan.gms.core.sitemanage.account.dao.GoldMapper;
import com.taoyuan.gms.core.sitemanage.account.service.IGoldService;
import com.taoyuan.gms.model.entity.site.account.GoldEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GoldServiceImpl extends TyBaseServiceImpl<GoldMapper, GoldEntity> implements IGoldService {
    @Override
    public void addGold(Long memberId, BigDecimal goldNum) {
        GoldEntity entity = getGlodByMemberId(memberId);
        entity.setGold(entity.getGold().add(goldNum));
        saveOrUpdate(entity);
    }

    @Override
    public void reduceGold(Long memberId, BigDecimal goldNum) {
        GoldEntity entity = getGlodByMemberId(memberId);
        entity.setGold(entity.getGold().subtract(goldNum));
        saveOrUpdate(entity);
    }

    @Override
    public GoldEntity getGlodByMemberId(Long memberId) {
        QueryWrapper<GoldEntity> wrapper = new QueryWrapper<GoldEntity>();
        wrapper.lambda().eq(GoldEntity::getMemberId, memberId);
        return (GoldEntity) getObj(wrapper);
    }
}
