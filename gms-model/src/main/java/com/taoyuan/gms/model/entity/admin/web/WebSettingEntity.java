package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 网站配置
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_websetting")
public class WebSettingEntity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id = 1l;

    //名称
    private String name;

    //关键字
    private String keyWord;

    //描述
    private String description;

    //金币和人民币兑换比例
    private Long exchangePropor;

    //兑换开关
    private boolean enableExchange;

    //代理充值折扣
    private int proxyRechargeDiscount;

    //是否能24小时兑换
    private boolean enable24Recharge;

    //禁止兑换起始时间
//    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private String forbidRechargeBeginTime;

    //禁止兑换结束时间
//    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private String forbidRechargeEndTime;

    //注册赠送金币
    private int registOfferGold;

    //流水计算天数
    private int serialCalDays;

    //充值经验兑换比例
    private Double rechargeExperiencePropor;

    //免费兑换倍数
    private int freeExchangeTimes;

    //获得成长值比例
    private Double gainGrowPropor;

    //超出手续费
    private Double overrangingFee;

    //下线投注工资比例
    private Double subordinateCathecticWagesPropor;

    //投注工资比例
    private Double cathecticWagesPropor;

    //亏损返利比例
    private Double lossRebatePropor;

    //首充返利比例
    private Double firstFillRebatePropor;

    //报名费
    private Double entryFee;

    //可得虚拟币
    private int virtualCornAvailableCount;

    //虚拟币可得到次数
    private int virtualCornAvailableTimes;

    private Long createUser;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Long updateUser;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

    public void update(WebSettingEntity entity){
        setCreateUser(null==entity.getCreateUser()?createUser:entity.getCreateUser());
        setCreateTime(null==entity.getCreateTime()?createTime:entity.getCreateTime());
        setCathecticWagesPropor(null==entity.getCathecticWagesPropor()?cathecticWagesPropor:entity.getCathecticWagesPropor());
        setDescription(null==entity.getDescription()?description:entity.getDescription());
        setEnable24Recharge(entity.isEnable24Recharge());
        setEntryFee(null==entity.getEntryFee()?entryFee:entity.getEntryFee());
        setEnableExchange(entity.isEnableExchange());
        setFirstFillRebatePropor(null==entity.getFirstFillRebatePropor()?firstFillRebatePropor:entity.getFirstFillRebatePropor());
        setForbidRechargeBeginTime(null==entity.getForbidRechargeBeginTime()?forbidRechargeBeginTime:entity.getForbidRechargeBeginTime());
        setForbidRechargeEndTime(null==entity.getForbidRechargeEndTime()?forbidRechargeEndTime:entity.getForbidRechargeEndTime());
        setExchangePropor(null==entity.getExchangePropor()?exchangePropor:entity.getExchangePropor());
        setFreeExchangeTimes(entity.getFreeExchangeTimes()!=0?entity.getFreeExchangeTimes():freeExchangeTimes);
        setGainGrowPropor(entity.getGainGrowPropor()!=0d?entity.getGainGrowPropor():gainGrowPropor);
        setId(null==entity.getId()?id:entity.getId());
        setKeyWord(null==entity.getKeyWord()?keyWord:entity.getKeyWord());
        setLossRebatePropor(null==entity.getLossRebatePropor()?lossRebatePropor:entity.getLossRebatePropor());
        setName(null==entity.getName()?name:entity.getName());
        setOverrangingFee(null==entity.getOverrangingFee()?overrangingFee:overrangingFee);
        setProxyRechargeDiscount(entity.getProxyRechargeDiscount());
        setRechargeExperiencePropor(entity.getRechargeExperiencePropor());
        setSerialCalDays(0==entity.getSerialCalDays()?serialCalDays:entity.getSerialCalDays());
        setRegistOfferGold(0==entity.getRegistOfferGold()?registOfferGold:entity.getRegistOfferGold());
        setSubordinateCathecticWagesPropor(null==entity.getSubordinateCathecticWagesPropor()?subordinateCathecticWagesPropor:entity.getSubordinateCathecticWagesPropor());
        setVirtualCornAvailableCount(0==entity.getVirtualCornAvailableCount()?virtualCornAvailableCount:entity.getVirtualCornAvailableCount());
        setVirtualCornAvailableTimes(0==entity.getVirtualCornAvailableTimes()?virtualCornAvailableTimes:entity.getVirtualCornAvailableTimes());
    }

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date createTime;
//
//    private Long updateUser;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date updateTime;
}
