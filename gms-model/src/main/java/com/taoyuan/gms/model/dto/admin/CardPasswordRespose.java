package com.taoyuan.gms.model.dto.admin;

import java.util.Date;

public class CardPasswordRespose {

    private Long id;

    //卡类型,1:10元会员卡，2:20元会员卡，3:30元会员卡，4:红钻会员卡
    private int cardType;

    //卡id
    private String cardId;

    //卡密码
    private String cardPassword;

    //所属者
    private String owner;

    //状态；1未充值，2已充值(回收),3已注销(金额回收)
    private int status;

    //兑奖ID
    private String rechargeId;

    //兑奖人姓名
    private String rechargeName;

    //生成兑换时间
    private Date startTime;

    //使用注销时间
    private Date endTime;

    //创建者
    private Long createUser;

    private Date createTime;

}
