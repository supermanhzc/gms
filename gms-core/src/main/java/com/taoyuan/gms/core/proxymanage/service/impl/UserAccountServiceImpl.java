package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.UserAccountMapper;
import com.taoyuan.gms.core.proxymanage.service.IUserAccountService;
import com.taoyuan.gms.model.entity.UserAccountEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccountEntity> implements IUserAccountService {
}
