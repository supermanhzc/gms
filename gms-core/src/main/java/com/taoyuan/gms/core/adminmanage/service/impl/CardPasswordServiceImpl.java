package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.CardPasswordMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CardPasswordServiceImpl extends ServiceImpl<CardPasswordMapper, CardPasswordEntity> implements ICardPasswordService {
    @Override
    public CardPasswordEntity getCardPasswordById(String id) {
        if(StringUtils.isEmpty(id)){
            return null;
        }

        QueryWrapper<CardPasswordEntity> wrapper = new QueryWrapper<CardPasswordEntity>();
        wrapper.lambda().eq(CardPasswordEntity::getCardId, id);
        return getOne(wrapper);
    }
}
