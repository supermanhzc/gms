package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;

public interface ICardPasswordService extends IService<CardPasswordEntity> {

    public CardPasswordEntity getCardPasswordById(String id);
}
