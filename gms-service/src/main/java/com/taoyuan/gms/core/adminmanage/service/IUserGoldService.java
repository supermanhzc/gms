package com.taoyuan.gms.core.adminmanage.service;

import com.taoyuan.framework.bs.service.TyBaseService;
import com.taoyuan.gms.model.entity.admin.account.UserGoldEntity;

import java.math.BigDecimal;

public interface IUserGoldService extends TyBaseService<UserGoldEntity>
{
    boolean add(Long id, BigDecimal gold);
    
    boolean deduct(Long id, BigDecimal gold);
}
