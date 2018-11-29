package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeCardPwdEntity;

public interface IExchargeCardPwdService extends IService<ExchargeCardPwdEntity> {

    ExchargeCardPwdEntity getById(Long id);

    ExchargeCardPwdEntity getByOrderId(int id);
}
