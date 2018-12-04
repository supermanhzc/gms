package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.UserLoginMapper;
import com.taoyuan.gms.core.adminmanage.service.IUserLoginService;
import com.taoyuan.gms.model.entity.admin.UserLoginEntity;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLoginEntity> implements IUserLoginService {
}
