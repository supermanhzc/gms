package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.ProxyOperMapper;
import com.taoyuan.gms.core.adminmanage.service.IProxyOperService;
import com.taoyuan.gms.model.entity.admin.ProxyOperEntity;
import org.springframework.stereotype.Service;

@Service
public class ProxyOperServiceImpl extends ServiceImpl<ProxyOperMapper, ProxyOperEntity> implements IProxyOperService {
}
