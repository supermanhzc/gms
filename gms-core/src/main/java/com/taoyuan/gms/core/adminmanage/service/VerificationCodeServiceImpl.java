package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.VerificationCodeMapper;
import com.taoyuan.gms.model.entity.admin.VerificationCodeEntity;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper,VerificationCodeEntity> implements IVerificationCodeService{
}
