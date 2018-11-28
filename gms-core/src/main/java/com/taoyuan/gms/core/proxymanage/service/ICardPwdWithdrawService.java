package com.taoyuan.gms.core.proxymanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;

public interface ICardPwdWithdrawService extends IService<CardPwdWithdrawEntity> {

    IPage getLatest10();
}
