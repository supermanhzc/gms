package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

/**
 * 管理员登陆
 */
@Data
public class AdminLoginDto {
    private String id;

    //用户名
    private String name;

    //状态
    private String status;

    //IP
    private String ip;

    //时间
    private String time;
}
