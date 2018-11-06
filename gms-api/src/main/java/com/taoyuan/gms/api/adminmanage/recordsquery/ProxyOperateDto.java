package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

/**
 * 代理操作
 */
@Data
public class ProxyOperateDto {
    //代理名称
    private String prxyName;

    //变动金额
    private String changeMoney;

    //余额
    private String balance;

    //类型
    private String operateType;

    //描述
    private String description;

    //时间
    private String time;
}
