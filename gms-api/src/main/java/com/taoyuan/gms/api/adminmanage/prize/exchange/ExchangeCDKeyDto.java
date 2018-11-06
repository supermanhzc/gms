package com.taoyuan.gms.api.adminmanage.prize.exchange;

import lombok.Data;

@Data
public class ExchangeCDKeyDto {

    //订单号
    private long id;

    //卡密ID
    private String ckKeyId;

    //卡密密码
    private String ckKeyPwd;

    //卡密类型
    private String ckKeyType;

    //兑奖ID
    private String exchangeId;

    //卡密状态
    private String ckKeyStatus;

    //兑换时间
    private String exchangeTime;

    //回收代理
    private String callbackProxy;

    //回收时间
    private String callbackTime;

    //冻结、解冻
    private String status;

}