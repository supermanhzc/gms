package com.taoyuan.gms.core.proxymanage.service.impl;

import org.springframework.stereotype.Service;

import com.taoyuan.framework.bs.service.TyBaseServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.ProxySaleStatisticMapper;
import com.taoyuan.gms.core.proxymanage.service.IProxySaleStatisticService;
import com.taoyuan.gms.model.entity.proxy.ProxySaleStatisticEntity;

@Service
public class ProxySaleStatisticServiceImpl extends TyBaseServiceImpl<ProxySaleStatisticMapper,
        ProxySaleStatisticEntity> implements IProxySaleStatisticService {
}
