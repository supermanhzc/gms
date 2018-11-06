package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

/**
 * 首充返利
 */
@Data
public class FirstchargeRebateDto {
    private String id;

    //昵称
    private String nickName;

    //昨日首充金额
    private String ytdFirstcharge;

    //返利额
    private String rebate;

    //日期
    private String date;

    //状态
    private String status;
}
