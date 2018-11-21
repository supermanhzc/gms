package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_cdkey")
public class CDKeyEntity {

    private Long id;

    //卡名称
    private String name;

    //面额
    private double denomination;

    //1:充值卡,2:兑换卡,3:会员卡
    private int cardType;

    //自定义卡头
    private String cardHead;

    //随机位数
    private int randomFigure;

    //根据位数生成的随机数
    private String randomNumStr;

    //卡头和随机数拼接的卡号
    private String cardId;

    //充值卡使用间隔
    private int useInterval;

    //充值卡代理可生成
    private int generateByProxy;

    //充值卡金豆
    private double goldenBean;

    //充值卡经验
    private double experience;

    //会员卡每日获得金币
    private double goldCoinEveryDay;

    //会员卡领取时长
    private long drawDuration;

    //会员卡图标
    private String icon;

    //创建者
    private Long createUser;

    //使用者
    private Long memberId;
}
