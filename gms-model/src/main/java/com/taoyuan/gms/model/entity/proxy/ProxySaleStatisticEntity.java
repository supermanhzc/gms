package com.taoyuan.gms.model.entity.proxy;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "proxy_salestatistic")
public class ProxySaleStatisticEntity {
    private Long id;

    //日期
    private LocalDate date;

    //卡密,表示自己创建的充值卡被会员充值使用的金额，按卡密的充值时间
    private BigDecimal cardPwd;

    //代充
    private BigDecimal recharge;

    //回收
    private BigDecimal withdraw;
}
