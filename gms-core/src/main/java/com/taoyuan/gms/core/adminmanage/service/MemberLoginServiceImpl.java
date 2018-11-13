package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.MemberLoginMapper;
import com.taoyuan.gms.model.entity.admin.UserLoginEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginServiceImpl extends ServiceImpl<MemberLoginMapper, UserLoginEntity> implements IMemberLoginService{
}
