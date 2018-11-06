package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

/**
 * 会员登陆
 */
@Data
public class MemberLoginDto {
    //ID
    private String id;

    //昵称
    private String nickName;

    //状态
    private String status;

    //IP
    private String ip;

    //地址
    private String addr;

    //时间
    private String time;
}
