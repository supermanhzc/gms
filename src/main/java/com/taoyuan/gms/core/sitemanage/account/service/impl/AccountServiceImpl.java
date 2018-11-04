package com.taoyuan.gms.core.sitemanage.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taoyuan.gms.api.sitemanage.account.AccountDto;
import com.taoyuan.gms.core.sitemanage.account.bo.AccountBo;
import com.taoyuan.gms.core.sitemanage.account.dao.AccountBoMapper;
import com.taoyuan.gms.core.sitemanage.account.service.AccountService;
import com.taoyuan.gms.util.CollectionsUtil;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountBoMapper accountBoMapper;
	
	@Override
	public List<AccountDto> getAccounts() {
		List<AccountDto> accountDtos = null;
		List<AccountBo> accounts = accountBoMapper.selectAll();
		if(CollectionsUtil.isNotEmpty(accounts)) {
			accountDtos = new ArrayList<AccountDto>();
			for(AccountBo accountBo :  accounts) {
				AccountDto accountDto = new AccountDto();
				accountDto.setUsername(accountBo.getUsername());
				accountDto.setName(accountDto.getName());
				accountDto.setPhone(accountBo.getPhone());
				accountDtos.add(accountDto);
			}
		}
		return accountDtos;
	}

}
