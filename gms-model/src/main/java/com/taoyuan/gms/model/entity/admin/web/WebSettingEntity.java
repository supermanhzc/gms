package com.taoyuan.gms.model.entity.admin.web;

import com.taoyuan.framework.common.entity.RecordEntity;

/**
 * 网站配置
 */
public class WebSettingEntity extends RecordEntity{
    //名称
    private String name;

    //关键字
    private String keyWord;

    //描述
    private String desc;

    //金币和人民币兑换比例
    private String exchangePropor;

    //兑换开关
    private String enableExchange;

    //代理充值折扣
    private String proxyRechargeDiscount;

    //是否能24小时兑换
    private boolean enable24Recharge;

    //禁止兑换起始时间
    private String forbidRechargeBeginTime;

    //禁止兑换结束时间
    private String forbidRechargeEndTime;

    //注册赠送金币
    private String registOfferGold;

    //流水计算天数
    private String serialCalDays;

    //充值经验兑换比例
    private String rechargeExperiencePropor;

    //免费兑换倍数
    private int freeExchangeTimes;

    //获得成长值比例
    private double gainGrowPropor;

    //超出手续费
    private double overrangingFee;

    //下线投注工资比例
    private double subordinateCathecticWagesPropor;

    //投注工资比例
    private double cathecticWagesPropor;

    //亏损返利比例
    private double lossRebatePropor;

    //首充返利比例
    private double firstFillRebatePropor;

    //报名费
    private double entryFee;

    //可得虚拟币
    private int virtualCornAvailableCount;

    //虚拟币可得到次数
    private int virtualCornAvailableTimes;
}
