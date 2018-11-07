package com.taoyuan.gms.core.sitemanage.account.service.impl;

import com.taoyuan.gms.model.entity.site.account.AccountEntity;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.sitemanage.account.dao.AccountBoMapper;
import com.taoyuan.gms.core.sitemanage.account.service.AccountService;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountBoMapper, AccountEntity> implements AccountService {

}
