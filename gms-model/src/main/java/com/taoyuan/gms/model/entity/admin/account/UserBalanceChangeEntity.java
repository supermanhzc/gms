package com.taoyuan.gms.model.entity.admin.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "gms_user_balance_change")
public class UserBalanceChangeEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_id")
    private Long userId;
    private Integer type;
    private String descp;
    @TableField(value = "balance_change")
    private BigDecimal balanceChange;
    private BigDecimal balance;
    private BigDecimal bank;
    @TableField(value = "update_user")
    private Long updateUser;


}
