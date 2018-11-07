package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

/**
 * 销售明细
 */
@Data
public class SaleDetailDto {
    //代理名称
    private String proxyName;

    //时间
    private String time;

    //代充金额
    private String substituteMoney;

    //回收金额
    private String callbackMoney;

    //收益
    private String income;
}
