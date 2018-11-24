package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "admin_cardpassword")
public class CardPasswordEntity {
    private Long id;

    //卡类型
    private int cardType;

    //卡id
    private String cardId;

    //卡密码
    private String cardPassword;

    //所属者
    private String owner;

    //状态；1未充值，2已充值,3已注销
    private int status;

    //兑奖ID
    private String rechargeId;

    //生成兑换时间
    private Timestamp startTime;

    //使用注销时间
    private  Timestamp endTime;

}
