package com.taoyuan.gms.core.sitemanage.account.controller;

import java.util.Map;

import com.taoyuan.gms.api.site.account.AccountApi;
import com.taoyuan.gms.model.entity.site.account.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.gms.core.sitemanage.account.service.AccountService;

@RestController
public class AccountController implements AccountApi {

	@Autowired
	private AccountService accountService;

	@Override
	public IPage<Map<String, Object>> getAccounts() {

		return accountService.pageMaps(new Page<AccountEntity>(1, 10), null);
	}

	@Override
	public Map<String, Object> getAccount(Long id) throws Exception {
		if(id < 0){
			throw new Exception("id must be long.");
		}
		return accountService.getMap(new QueryWrapper<AccountEntity>().eq("id", id));
	}

}
