package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.ProxyOperMapper;
import com.taoyuan.gms.model.entity.admin.ProxyOperEntity;
import org.springframework.stereotype.Service;

@Service
public class ProxyOperServiceImpl extends ServiceImpl<ProxyOperMapper, ProxyOperEntity> implements IProxyOperService {
}
