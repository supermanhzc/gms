package com.taoyuan.gms.api.admin.recordsquery;

import lombok.Data;

@Data
public class SaleStatisticDto {
    private String proxyName;

    private String cdKeyMoney;

    private String sustituteMoney;

    private String callbackMoney;

    private String income;
}
