package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_proxyoperation")
public class ProxyOperEntity {
    private Long id;

    private Long proxyId;

    private String proxyName;

    //账户变动
    private Double moneyChanged;

    //余额
    private Double account;

    //类型
    private int type;

    //描述
    private String description;

    private Timestamp time;
}
