package com.taoyuan.gms.model.entity.admin.prize;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "admin_exchargecardowd")
public class ExchargeCardPwdEntity implements Serializable {

    private Long id;

    //订单id
    private int orderId;

    //卡类型,1:10元会员卡，2:20元会员卡，3:30元会员卡，4:红钻会员卡
    private int cardType;

    //卡id
    private String cardId;

    //卡密码
    private String cardPassword;

    //会员ID
    private Long memberId;

    //会员昵称
    private  String memberNickName;

    //状态；1未兑换，2冻结、3解冻，4已兑换
    private int status;

    //生成兑换时间
    private Date startTime;

    //处理结束时间
    private Date endTime;

    //回收代理id
    private Long withdrawProxyId;

    //回收代理名称
    private Long withdrawProxyName;

}