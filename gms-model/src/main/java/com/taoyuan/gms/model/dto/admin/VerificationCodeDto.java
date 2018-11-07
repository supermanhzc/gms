package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

/**
 * 验证码
 */
@Data
public class VerificationCodeDto {
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
}
