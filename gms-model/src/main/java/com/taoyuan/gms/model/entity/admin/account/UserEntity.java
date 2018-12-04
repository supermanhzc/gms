package com.taoyuan.gms.model.entity.admin.account;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.taoyuan.framework.common.entity.TyBaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "gms_user")
public class UserEntity extends TyBaseEntity {

    @TableId
    private Long id;
    @TableField(value = "nick_name")
    private String nickName;
    private String qq;
    private BigDecimal balance;
    private BigDecimal bank;
    private Integer experience;
    @TableField(value = "distrib_money", exist = false)
    private BigDecimal distribMoney;
    @TableField(value = "referee_id")
    private Long refereeId;
}
