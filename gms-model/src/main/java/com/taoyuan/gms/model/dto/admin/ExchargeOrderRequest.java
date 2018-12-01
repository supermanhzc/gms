package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

@Data
public class ExchargeOrderRequest {

    //奖品名称，必填
    private String prizeName;

    //兑换数量
    private int exchangeNum;
}
