package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.bs.service.TyBaseServiceImpl;
import com.taoyuan.framework.common.exception.TyBusinessException;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.gms.core.adminmanage.dao.UserGoldMapper;
import com.taoyuan.gms.core.adminmanage.service.IUserGoldService;
import com.taoyuan.gms.model.entity.admin.account.UserGoldEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class UserGoldServiceImpl
    extends TyBaseServiceImpl<UserGoldMapper, UserGoldEntity>
    implements IUserGoldService
{
    
    @Override
    public boolean add(Long id, BigDecimal gold)
    {
        QueryWrapper<UserGoldEntity> wrapper =
            new QueryWrapper<UserGoldEntity>();
        wrapper.lambda().eq(UserGoldEntity::getId, id);
        UserGoldEntity entity = (UserGoldEntity)getOne(wrapper);
        if (null != entity)
        {
            entity.setGold(entity.getGold().add(gold));
        }
        else
        {
            entity.setGold(gold);
        }
        entity.setDate(new Date());
        return saveOrUpdate(entity);
    }
    
    @Override
    public boolean deduct(Long id, BigDecimal gold)
        throws TyBusinessException
    {
        QueryWrapper<UserGoldEntity> wrapper =
            new QueryWrapper<UserGoldEntity>();
        wrapper.lambda().eq(UserGoldEntity::getId, id);
        UserGoldEntity entity = (UserGoldEntity)getOne(wrapper);
        if (null != entity)
        {
            entity.setGold(entity.getGold().subtract(gold));
        }
        else
        {
            throw new TyBusinessException("金币余额不足。");
        }
        entity.setDate(new Date());
        return saveOrUpdate(entity);
    }
}
