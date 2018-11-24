package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.CardPasswordMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import org.springframework.stereotype.Service;

@Service
public class CardPasswordServiceImpl extends ServiceImpl<CardPasswordMapper, CardPasswordEntity> implements ICardPasswordService {
}
