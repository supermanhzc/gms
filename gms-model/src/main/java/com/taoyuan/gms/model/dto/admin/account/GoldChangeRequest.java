package com.taoyuan.gms.model.dto.admin.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoldChangeRequest{
    //用户id
    private Long id;

    //金币
    private BigDecimal gold;
}
