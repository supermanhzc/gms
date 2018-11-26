package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 验证码
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_verificationcode")
public class VerificationCodeEntity implements Serializable {

    //ID
    private long id;

    //接口名称:短信
    private String infName;

    //类型:1注册,2找回密码,3兑奖
    private int type;

    //验证码接收终端，当前默认为手机号码
    private String dest;

    //验证码
    private String vCode;

    //时间
    private Timestamp time;
}
