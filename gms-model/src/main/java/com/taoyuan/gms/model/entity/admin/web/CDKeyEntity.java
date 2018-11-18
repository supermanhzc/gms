package com.taoyuan.gms.model.entity.admin.web;

public class CDKeyEntity {

    private Long id;

    //卡密id
    private Long keyId;

    //卡名称
    private String name;

    //面额
    private double denomination;

    //代理可生成
    private int generateByProxy;

    //金豆
    private double goldenBean;

    //经验
    private double experience;

    //会员卡
    private int isMemberCard;

    //自定义卡头
    private String cardHead;

    //随机位数
    private int randomFigure;

    //每日获得金币
    private double goldCoinEveryDay;

    //领取时长
    private long drawDuration;

    //图标
    private String icon;


}
