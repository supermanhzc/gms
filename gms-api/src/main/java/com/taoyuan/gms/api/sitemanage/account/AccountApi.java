package com.taoyuan.gms.api.sitemanage.account;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

@Api(value = "账号服务")
@RequestMapping("/sitemanage/account")
public interface AccountApi {

	/**
	 * 查询系统中所有的管理员账号信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<AccountDto> getAccounts();

	/**
	 * 查询系统中指定管理员的账号信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public AccountDto getAccount(@PathVariable("id") Long id);

	/**
	 * 新增管理员的账号信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public boolean addAccount(@RequestBody AccountDto accountDto);

	/**
	 * 删除管理员的账号信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteAccount(@PathVariable("id") Long id);

	/**
	 * 更新管理员的账号信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public AccountDto updateAccount(@RequestBody AccountDto accountDto);
}
