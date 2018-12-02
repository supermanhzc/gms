package com.taoyuan.gms.model.entity.proxy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName(value = "proxy_goldenrecharge")
public class GoldenRechargeEntity {
    private Long id;

    private Long proxyId;

    private String proxyName;

    //会员ID
    private Long memberId;

    //昵称
    private String memberNickName;

    //金额
    private BigDecimal amount;

    //时间
    private Date time;

    //状态1:成功，2：已撤销
    private int status;

    //撤销状态，0已撤销,1未撤销
    private int withdraw;
}
