package com.taoyuan.gms.core.adminmanage.bo;

import lombok.Data;

/**
 * 验证码
 */
@Data
public class VerificationCodeBo {

    //ID
    private long id;

    //接口名称
    private String infName;

    //类型
    private String type;

    //验证码
    private String vCode;

    //时间
    private String time;

    private Long createUser;

    private String createTime;

    private Long updateUser;

    private String updateTime;
}
