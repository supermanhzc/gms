package com.taoyuan.gms.core.sitemanage.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taoyuan.gms.api.sitemanage.account.AccountDto;
import com.taoyuan.gms.common.util.CollectionsUtil;
import com.taoyuan.gms.core.sitemanage.account.bo.AccountBo;
import com.taoyuan.gms.core.sitemanage.account.dao.AccountBoMapper;
import com.taoyuan.gms.core.sitemanage.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountBoMapper accountBoMapper;

	@Override
	public List<AccountDto> getAccounts() {
		List<AccountDto> accountDtos = null;
		List<AccountBo> accounts = accountBoMapper.selectAll();
		if (CollectionsUtil.isNotEmpty(accounts)) {
			accountDtos = new ArrayList<AccountDto>();
			for (AccountBo accountBo : accounts) {
				AccountDto accountDto = new AccountDto();
				accountDto.setUsername(accountBo.getUsername());
				accountDto.setName(accountDto.getName());
				accountDto.setPhone(accountBo.getPhone());
				accountDtos.add(accountDto);
			}
		}
		return accountDtos;
	}

	@Override
	public boolean addAccount(AccountDto accountDto) {
		if (null != accountDto) {
			return accountBoMapper.insertSelective(this.convertToBo(accountDto)) > 0;
		}
		return false;
	}

	@Override
	public boolean deleteAccount(Long id) {
		return accountBoMapper.deleteByPrimaryKey(id) > 0;
	}

	public AccountDto getAccount(Long id) {
		return this.convertToDto(accountBoMapper.selectByPrimaryKey(id));
	}

	private AccountDto convertToDto(AccountBo accountBo) {
		AccountDto accountDto = new AccountDto();
		accountDto.setUsername(accountBo.getUsername());
		accountDto.setName(accountDto.getName());
		accountDto.setPhone(accountBo.getPhone());
		return accountDto;
	}

	private AccountBo convertToBo(AccountDto accountDto) {
		AccountBo accountBo = new AccountBo();
		accountBo.setName(accountDto.getName());
		accountBo.setUsername(accountDto.getUsername());
		accountBo.setPhone(accountDto.getPhone());
		return accountBo;
	}
}
