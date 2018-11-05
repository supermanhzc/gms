package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

@Data
public class ProxyOperateDto {
    private String prxyName;

    private String changeMoney;

    private String balance;

    private String operateType;

    private String description;

    private String time;
}
