package com.taoyuan.gms.model.entity.proxy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_proxyoperation")
public class ProxyOperEntity {
    private Long id;

    private Long proxyId;

    private String proxyName;

    //账户变动
    private BigDecimal moneyChanged;

    //余额
    private BigDecimal account;

    //类型：1代充，2充值，3提现，4登录，5回收, 6互转, 创建卡密
    private int type;

    //描述
    private String description;

    //ip
    private String ip;

    private Date time;
}
