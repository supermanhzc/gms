package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

@Data
public class ChipinWageDto {
    private String id;

    //昵称
    private String nickName;

    //有效流水
    private String effectiveFlow;

    //工资
    private String wage;

    //日期
    private String date;

    //状态
    private String status;
}
