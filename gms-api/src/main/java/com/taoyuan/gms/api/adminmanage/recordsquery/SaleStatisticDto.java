package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

/**
 * 销售统计
 */
@Data
public class SaleStatisticDto {
    //代理名称
    private String proxyName;

    //卡密金额
    private String cdKeyMoney;

    //代充金额
    private String sustituteMoney;

    //回收金额
    private String callbackMoney;

    //收益
    private String income;
}
