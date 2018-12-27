package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.bs.service.TyBaseServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.CardPwdCallbackMapper;
import com.taoyuan.gms.core.proxymanage.service.ICardPwdWithdrawService;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CardPwdWithdrawServiceImpl
    extends TyBaseServiceImpl<CardPwdCallbackMapper, CardPwdWithdrawEntity>
    implements ICardPwdWithdrawService
{
    @Override
    public IPage getLatest10()
    {
        Page page = new Page(1, 10);
        QueryWrapper<CardPwdWithdrawEntity> wrapper =
            new QueryWrapper<CardPwdWithdrawEntity>();
        wrapper.orderByDesc("time");
        
        return page(page, wrapper);
    }
    
    @Override
    public BigDecimal getStatistic(Long id, Date start, Date end)
    {
        QueryWrapper<CardPwdWithdrawEntity> wrapper =
            new QueryWrapper<CardPwdWithdrawEntity>();
        wrapper.lambda()
            .eq(CardPwdWithdrawEntity::getProxyId, id)
            .between(CardPwdWithdrawEntity::getTime, start, end);
        List<CardPwdWithdrawEntity> cardPwdWithdrawEntityList = list(wrapper);
        BigDecimal statistic = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(cardPwdWithdrawEntityList))
        {
            for (CardPwdWithdrawEntity entity : cardPwdWithdrawEntityList)
            {
                statistic.add(entity.getAmount());
            }
        }
        return statistic;
    }
}
