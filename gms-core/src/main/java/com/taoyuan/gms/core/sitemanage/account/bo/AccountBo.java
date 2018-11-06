package com.taoyuan.gms.core.sitemanage.account.bo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "sitemanage_account")
public class AccountBo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6138071947120689089L;

	@TableId(value = "id", type = IdType.AUTO)
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
