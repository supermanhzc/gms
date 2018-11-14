package com.taoyuan.gms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "gms_useraccount")
public class UserAccountEntity {
    private Long id;

    private Long userId;

    private String userName;

    private double account = 0;

    private Timestamp time;
}
