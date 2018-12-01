package com.taoyuan.gms.model.entity.admin.account;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "gms_user")
public class UserEntity {

    @TableId
    private Long id;
    @TableField(value = "nick_name")
    private String nickName;
    private String qq;
    private BigDecimal balance;
    private BigDecimal bank;
    private Integer experience;

//    private Integer totalRecharge;
//    private Integer totalAward;
//    private Integer childNumber;
}
