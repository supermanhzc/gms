package com.taoyuan.gms.core.sitemanage.account.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.sitemanage.account.bo.AccountBo;
import com.taoyuan.gms.core.sitemanage.account.dao.AccountBoMapper;
import com.taoyuan.gms.core.sitemanage.account.service.AccountService;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountBoMapper, AccountBo> implements AccountService {

}
