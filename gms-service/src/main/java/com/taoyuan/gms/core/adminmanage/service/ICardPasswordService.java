package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.admin.card.CardPasswordEntity;

public interface ICardPasswordService extends IService<CardPasswordEntity> {

    public CardPasswordEntity getByCardId(String id);

    public CardPasswordEntity getByCardIdAndPwd(String cardId,String password);
}
