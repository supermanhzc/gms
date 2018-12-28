package com.taoyuan.gms.core.proxymanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.framework.bs.service.TyBaseService;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;

import java.math.BigDecimal;
import java.util.Date;

public interface ICardPwdWithdrawService
    extends TyBaseService<CardPwdWithdrawEntity>
{
    
    IPage getLatest10();
    
    BigDecimal getStatistic(Long id, Date start, Date end);
}
