package com.taoyuan.gms.core.sitemanage.account.service;

import java.util.List;

import com.taoyuan.gms.api.sitemanage.account.AccountDto;

public interface AccountService {

	public List<AccountDto> getAccounts();

	public AccountDto getAccount(Long id);

	public boolean addAccount(AccountDto accountDto);

	public boolean deleteAccount(Long id);
}
