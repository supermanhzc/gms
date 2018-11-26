package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "admin_cardtype")
public class CardTypeEntity {

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Long updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    //使用者
    private Long memberId;

    public void update(CardTypeEntity other, String randomNumStr) {
        if (other.getCardHead() != null) {
            this.setCardHead(other.getCardHead());
        }

        if (other.getCardId() != null) {
            this.setCardId(other.getCardId());
        }

        if (other.getCardType() != 0) {
            this.setCardType(other.getCardType());
        }

        if (other.getDenomination() != 0) {
            this.setDenomination(other.getDenomination());
        }

        if (other.getDrawDuration() != 0) {
            this.setDrawDuration(other.getDrawDuration());
        }

        if (other.getExperience() != 0) {
            this.setExperience(other.getExperience());
        }

        if (other.getGenerateByProxy() != 0) {
            this.setGenerateByProxy(other.getGenerateByProxy());
        }

        if (other.getGoldCoinEveryDay() != 0) {
            this.setGoldCoinEveryDay(other.getGoldCoinEveryDay());
        }

        if (other.getGoldenBean() != 0) {
            this.setGoldenBean(other.getGoldenBean());
        }

        if (other.getIcon() != null) {
            this.setIcon(other.getIcon());
        }

        if (other.getMemberId() != null) {
            this.setMemberId(other.getMemberId());
        }

        if (other.getName() != null) {
            this.setName(other.getName());
        }

        if (other.getRandomFigure() != 0) {
            this.setRandomFigure(other.getRandomFigure());
        }

        if (randomNumStr != null) {
            this.setRandomNumStr(randomNumStr);
            this.setCardId(getCardHead() + randomNumStr);
        }else{
            this.setCardId(getCardHead() + getRandomNumStr());
        }

        if (other.getUseInterval() != 0) {
            this.setUseInterval(other.getUseInterval());
        }
    }

}
