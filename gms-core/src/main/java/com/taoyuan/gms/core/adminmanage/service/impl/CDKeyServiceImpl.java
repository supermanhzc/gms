package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.CDKeyMapper;
import com.taoyuan.gms.core.adminmanage.service.ICDkeyService;
import com.taoyuan.gms.model.entity.admin.web.CDKeyEntity;
import org.springframework.stereotype.Service;

@Service
public class CDKeyServiceImpl extends ServiceImpl<CDKeyMapper, CDKeyEntity> implements ICDkeyService {
}