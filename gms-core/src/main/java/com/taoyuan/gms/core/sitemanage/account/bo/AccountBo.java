package com.taoyuan.gms.core.sitemanage.account.bo;

import lombok.Data;

@Data
public class AccountBo {

	private long id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String phone;
	
	private Long createUser;
	
	private String createTime;
	
	private Long updateUser;
	
	private String updateTime;
}
