package com.taoyuan.gms.core.sitemanage.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.taoyuan.gms.api.sitemanage.account.AccountApi;
import com.taoyuan.gms.api.sitemanage.account.AccountDto;
import com.taoyuan.gms.core.sitemanage.account.service.AccountService;

@RestController
public class AccountController implements AccountApi {

	@Autowired
	private AccountService accountServiceImpl;
	
	@Override
	public List<AccountDto> getAccounts() {
		return accountServiceImpl.getAccounts();
	}

	@Override
	public AccountDto getAccount(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto addAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto deleteAccount(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto updateAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
