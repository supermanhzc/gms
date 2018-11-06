package com.taoyuan.gms.api.adminmanage.prize.prize;

import lombok.Data;

@Data
public class PrizeDto {
    private long id;

    //奖品名称
    private String name;

    //奖品类目
    private String type;

    //自动发货
    private boolean autoDispatch;

    //卡密类型
    private String keyType;

    //基准价格
    private double basicPrice;

    //库存
    private int stock;

    //已兑出
    private String converted;

}
