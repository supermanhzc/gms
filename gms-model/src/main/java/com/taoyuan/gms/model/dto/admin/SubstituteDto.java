package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

/**
 * 代充记录
 */
@Data
public class SubstituteDto {
    //代理名称
    private String proxyName;

    //会员ID
    private String memberId;

    //会员昵称
    private String memberNickName;

    //金额
    private String money;

    //时间
    private String time;

    //状态
    private String status;
}

