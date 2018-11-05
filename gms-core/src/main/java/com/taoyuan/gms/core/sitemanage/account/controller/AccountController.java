package com.taoyuan.gms.core.sitemanage.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.taoyuan.gms.api.sitemanage.account.AccountApi;
import com.taoyuan.gms.api.sitemanage.account.AccountDto;
import com.taoyuan.gms.core.sitemanage.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountController implements AccountApi {

	@Autowired
	private AccountService accountService;

	@Override
	public List<AccountDto> getAccounts() {
		return accountService.getAccounts();
	}

	@Override
	public AccountDto getAccount(Long id) {
		log.info("begin to get account by id: {}", id);
		return accountService.getAccount(id);
	}

	@Override
	public boolean addAccount(AccountDto accountDto) {
		return accountService.addAccount(accountDto);
	}

	@Override
	public boolean deleteAccount(Long id) {
		return accountService.deleteAccount(id);
	}

	@Override
	public AccountDto updateAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
