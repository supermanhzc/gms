package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_levelsetting")
public class LevelSettingEntity {
    private int id;

    //名称vip0、vip1、vip2、vip3、vip4、vip5、vip6、vip7
    private String name;

    //经验
    private int experience;

    //救济金币
    private int relieveGoldenCoin;

    //领取次数
    private int drawTimes;

    //领取条件
    private int drawCondition;

    //充值提成
    private int rechargeCommission;

    //兑奖折扣
    private int cashPrizeDiscount;
}
