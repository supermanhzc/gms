package com.taoyuan.gms.api.adminmanage.prize.exchange;

import lombok.Data;

@Data
public class ExchangeOrderDto {
    private long id;

    //订单号
    private long orderId;

    //会员ID
    private String memberId;

    //会员昵称
    private String memberNickName;

    //奖品名称
    private String prizeName;

    //兑换数量
    private String exchangeNum;

    //兑换单价
    private String exchangeSinglePrice;

    //兑换时间
    private String exchangeTime;

    //状态
    private String status;

}
