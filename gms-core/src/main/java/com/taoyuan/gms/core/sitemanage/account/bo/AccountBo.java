package com.taoyuan.gms.core.sitemanage.account.bo;

import java.util.Date;

import lombok.Data;

@Data
public class AccountBo {

	private long id;

	private String username;

	private String password;

	private String name;

	private String phone;

	private Long createUser;

	private Date createTime;

	private Long updateUser;

	private Date updateTime;
}
