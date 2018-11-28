package com.taoyuan.gms.model.entity.proxy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "proxy_fundexchange")
public class FundExchangeEntity {

    private Long id;

    //1：转出，2：转入，必填
    private int type;

    //金额，必填
    private BigDecimal money;

    //对方，必填
    private Long opposite;

    //当前操作代理,非必填
    private Long proxyId;

    //时间
    private Date time;
}
