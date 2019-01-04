package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.ExchargeOrderMapper;
import com.taoyuan.gms.core.adminmanage.service.IExchargeOrderService;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeOrderEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExchargeOrderServiceImpl extends ServiceImpl<ExchargeOrderMapper, ExchargeOrderEntity> implements IExchargeOrderService {
    @Override
    public ExchargeOrderEntity getByOrderId(int id) {
        QueryWrapper<ExchargeOrderEntity> wrapper = new QueryWrapper<ExchargeOrderEntity>();
        wrapper.lambda().eq(ExchargeOrderEntity::getOrderId, id);
        return getOne(wrapper);
    }

    @Override
    public BigDecimal getExchargeSumByUserId(Long id) {
        QueryWrapper<ExchargeOrderEntity> wrapper = new QueryWrapper<ExchargeOrderEntity>();
        wrapper.lambda().eq(ExchargeOrderEntity::getMemberId,id).eq(ExchargeOrderEntity::getStatus,2);
        List<ExchargeOrderEntity> exchargeOrderEntityList = list(wrapper);
        if(CollectionUtils.isEmpty(exchargeOrderEntityList)){
            return BigDecimal.ZERO;
        }

        BigDecimal result = BigDecimal.ZERO;
        for(ExchargeOrderEntity entity:exchargeOrderEntityList){
            BigDecimal totalPrice = entity.getExchangeSinglePrice().multiply(BigDecimal.valueOf(entity.getExchangeNum()));
            result = result.add(totalPrice);
        }
        return result;
    }
}
