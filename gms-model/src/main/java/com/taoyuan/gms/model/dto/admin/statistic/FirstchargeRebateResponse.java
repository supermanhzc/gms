package com.taoyuan.gms.model.dto.admin.statistic;

import lombok.Data;

import java.util.Date;

/**
 * 首充返利
 */
@Data
public class FirstchargeRebateResponse {
    //会员id
    private String memberId;

    //昵称
    private String memberName;

    //昨日首充金额
    private String ytdFirstcharge;

    //返利额
    private String rebate;

    //日期
    private Date date;

    //状态
    private int status;
}
