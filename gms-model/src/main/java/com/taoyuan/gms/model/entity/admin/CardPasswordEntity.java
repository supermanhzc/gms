package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName(value = "admin_cardpassword")
public class CardPasswordEntity {
    private Long id;

    //卡类型,1:10元会员卡，2:20元会员卡，3:30元会员卡，4:红钻会员卡
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
    private Date startTime;

    //使用注销时间
    private Date endTime;

    //创建者
    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public BigDecimal getMoney() {
        switch (cardType) {
            case 1:
                return BigDecimal.valueOf(10);
            case 2:
                return BigDecimal.valueOf(20);
            case 3:
                return BigDecimal.valueOf(30);
            case 4:
                return BigDecimal.valueOf(100);
            default:
                break;
        }

        return BigDecimal.ZERO;
    }
}
