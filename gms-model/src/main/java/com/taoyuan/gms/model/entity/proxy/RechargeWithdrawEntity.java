package com.taoyuan.gms.model.entity.proxy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "proxy_rechargewithdraw")
public class RechargeWithdrawEntity {
    private Long id;

    private Long proxyId;

    //类型，1充值，2提现，必填
    private int type;

    //充值或者提现金额，必填
    private BigDecimal money;

    //余额
    private BigDecimal balance;

    //铺货
    private BigDecimal distribution;

    //状态，1待处理，2已处理，3取消
    private int status;

    //创建时间
    private Date createTime;

    //处理结束时间
    private Date finishTime;

}
