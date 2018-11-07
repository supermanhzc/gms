package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 验证码
 */
@Data
@TableName(value = "admin_verificationcode")
public class VerificationCodeEntity implements Serializable {

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
