package com.taoyuan.gms.model.entity.proxy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 首充返利
 */
@Data
@TableName(value = "proxy_firstchargerebate")
public class FirstchargeRebateEntity {
    private Long id;

    //会员id
    private Long memberId;

    //昵称
    private String memberName;

    //昨日首充金额
    private BigDecimal ytdFirstcharge;

    //返利额
    private BigDecimal rebate;

    //日期
    private Date date;

    //状态,1未领取，2已领取
    private int status;
}
