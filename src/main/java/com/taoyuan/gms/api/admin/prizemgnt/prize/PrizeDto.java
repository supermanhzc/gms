package com.taoyuan.gms.api.admin.prizemgnt.prize;

import lombok.Data;

@Data
public class PrizeDto {
    private long id;

    private String name;

    private String type;

    private boolean autoDispatch;

    private String keyType;

    private double basicPrice;

    private double stock;

    private double converted;

}
