package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.CardPasswordMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.entity.admin.card.CardPasswordEntity;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CardPasswordServiceImpl
    extends ServiceImpl<CardPasswordMapper, CardPasswordEntity>
    implements ICardPasswordService
{
    @Override
    public CardPasswordEntity getByCardId(String id)
    {
        if (StringUtils.isEmpty(id))
        {
            return null;
        }
        
        QueryWrapper<CardPasswordEntity> wrapper =
            new QueryWrapper<CardPasswordEntity>();
        wrapper.lambda().eq(CardPasswordEntity::getCardId, id);
        return getOne(wrapper);
    }
    
    @Override
    public CardPasswordEntity getByCardIdAndPwd(String cardId, String password)
    {
        QueryWrapper<CardPasswordEntity> wrapper =
            new QueryWrapper<CardPasswordEntity>();
        wrapper.lambda()
            .eq(CardPasswordEntity::getCardId, cardId)
            .eq(CardPasswordEntity::getCardPassword, password);
        return getOne(wrapper);
    }
    
    @Override
    public BigDecimal getStatistic(Long id, Date start, Date end)
    {
        QueryWrapper<CardPasswordEntity> wrapper =
            new QueryWrapper<CardPasswordEntity>();
        wrapper.lambda()
            .eq(CardPasswordEntity::getCreateUser, id)
            .between(CardPasswordEntity::getEndTime, start, end);
        List<CardPasswordEntity> cardPasswordEntityList = list(wrapper);
        BigDecimal statistic = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(cardPasswordEntityList))
        {
            for (CardPasswordEntity entity : cardPasswordEntityList)
            {
                statistic.add(entity.getMoney());
            }
        }
        
        return statistic;
    }
}
