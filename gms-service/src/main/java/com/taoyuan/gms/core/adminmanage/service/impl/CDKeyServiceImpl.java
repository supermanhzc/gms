package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.CardTypeMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardMgntService;
import com.taoyuan.gms.model.entity.admin.web.CardTypeEntity;
import org.springframework.stereotype.Service;

@Service
public class CDKeyServiceImpl extends ServiceImpl<CardTypeMapper, CardTypeEntity> implements ICardMgntService {
}
