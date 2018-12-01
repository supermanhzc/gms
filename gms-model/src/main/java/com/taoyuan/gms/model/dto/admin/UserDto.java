package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String nickName;
    private String phone;
    private String name;
    private String qq;
    private String password;
    private String securityPassword;
    private boolean needAuth;
    private Integer refereeId;
}
