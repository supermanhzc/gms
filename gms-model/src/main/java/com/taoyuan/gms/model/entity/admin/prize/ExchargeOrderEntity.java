package com.taoyuan.gms.model.entity.admin.prize;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "admin_exchargeorder")
public class ExchargeOrderEntity implements Serializable {
    private Long id;

    //订单号
    private int orderId;

    //会员ID，必填
    private Long memberId;

    //会员昵称
    private String memberNickName;

    //奖品名称，必填
    private String prizeName;

    //兑换数量
    private int exchangeNum;

    //兑换单价
    private BigDecimal exchangeSinglePrice;

    //兑换时间
    private Date exchangeTime;

    //状态,1未发货，2已发货，3已取消
    private int status;

    //当前处理订单的人
    private Long processorId;

    //当前处理订单人名称
    private String processorName;
}
