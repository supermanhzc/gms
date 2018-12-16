package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.ProxyOperMapper;
import com.taoyuan.gms.core.proxymanage.service.IProxyOperService;
import com.taoyuan.gms.model.entity.proxy.ProxyOperEntity;
import org.springframework.stereotype.Service;

@Service
public class ProxyOperServiceImpl extends ServiceImpl<ProxyOperMapper, ProxyOperEntity> implements IProxyOperService {
}
