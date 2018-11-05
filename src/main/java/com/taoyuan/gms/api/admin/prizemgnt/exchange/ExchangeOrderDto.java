package com.taoyuan.gms.api.admin.prizemgnt.exchange;

import lombok.Data;

@Data
public class ExchangeOrderDto {
    private long id;

    private String memberId;

    private String memberNickName;

    private String prizeName;

    private String exchangeNum;

    private String exchangeSinglePrice;

    private String exchangeTime;

    private String status;

}
