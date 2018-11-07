package com.taoyuan.gms.api.site.account;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baomidou.mybatisplus.core.metadata.IPage;

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
	public IPage<Map<String, Object>> getAccounts();

	/**
	 * 查询系统中指定管理员账号信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Map<String, Object> getAccount(@PathVariable("id") Long id) throws Exception;
}
