package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "admin_juniorcommission")
public class JuniorCommissionEntity {
    private Long id;

    //会员ID
    private Long memberId;

    //昵称
    private String memberNickName;

    //有效流水
    private double ytdJuniorCommissionFlow;

    //工资
    private double wage;

    //日期
    private Date date;

    //状态
    private int status;
}
