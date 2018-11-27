package com.taoyuan.gms.model.entity.proxy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "proxy_cardpwdcallback")
public class CardPwdWithdrawEntity {
    private Long id;

    //代理Id
    private Long proxyId;

    //代理名称
    private String proxyName;

    //会员id
    private Long memberId;

    //会员昵称
    private String memberName;

    //回收数量
    private int count;

    //总金额
    private BigDecimal amount;

    private Date time;

}
