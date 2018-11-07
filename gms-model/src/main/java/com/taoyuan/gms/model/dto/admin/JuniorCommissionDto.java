package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

/**
 * 下线提成
 */
@Data
public class JuniorCommissionDto {
    //ID
    private String id;

    //昵称
    private String nickName;

    //昨日流水
    private String ytdFlow;

    //工资额
    private String wage;

    //日期
    private String date;

    //状态
    private String status;
}
