package com.taoyuan.gms.core.proxymanage.service;

import com.taoyuan.framework.bs.service.TyBaseService;
import com.taoyuan.gms.model.entity.proxy.FirstchargeRebateEntity;

public interface IFirstchargeRebateService extends TyBaseService<FirstchargeRebateEntity> {

    /**
     * 会员是否存在首充记录
     * @param id
     * @return
     */
    boolean exist(Long id);
}
