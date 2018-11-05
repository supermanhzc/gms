package com.taoyuan.gms.api.adminmanage.prizemgnt.exchange;

import lombok.Data;

@Data
public class ExchangeCDKeyDto {
    //订单号
    private long id;

    private String ckKeyId;

    private String ckKeyPwd;

    private String ckKeyType;

    private String exchangeId;

    private String ckKeyStatus;

    private String exchangeTime;

    private String callbackProxy;

    private String callbackTime;

    //冻结、解冻
    private String status;

}