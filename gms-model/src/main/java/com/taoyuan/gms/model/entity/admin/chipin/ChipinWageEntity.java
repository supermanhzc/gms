package com.taoyuan.gms.model.entity.admin.chipin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 投注工资
 */
@Data
@TableName(value = "admin_chipinwage")
public class ChipinWageEntity {
    private Long id;

    //会员ID
    private Long memberId;

    //昵称
    private String memberNickName;

    //有效流水
    private double effectiveFlow;

    //工资
    private double wage;

    //日期
    private Date date;

    //状态，1已领取，2未领取
    private int status;
}
