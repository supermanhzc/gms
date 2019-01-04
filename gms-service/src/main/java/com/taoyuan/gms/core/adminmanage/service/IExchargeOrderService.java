package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeOrderEntity;

import java.math.BigDecimal;

public interface IExchargeOrderService extends IService<ExchargeOrderEntity> {

//    ExchargeOrderEntity getById(Long id);

    ExchargeOrderEntity getByOrderId(int id);

    BigDecimal getExchargeSumByUserId(Long id);
}
