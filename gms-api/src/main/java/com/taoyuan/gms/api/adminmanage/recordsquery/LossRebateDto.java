package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

/**
 * 亏损返利
 */
@Data
public class LossRebateDto {
    //ID
    private String id;

    //昵称
    private String nickName;

    //昨日亏损额
    private String ytdLoss;

    //返利额
    private String rebate;

    //日期
    private String date;

    //状态
    private String status;
}
