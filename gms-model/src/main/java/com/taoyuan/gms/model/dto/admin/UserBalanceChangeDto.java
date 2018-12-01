package com.taoyuan.gms.model.dto.admin;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserBalanceChangeDto {

    private Long userId;
    private Integer type;
    private String descp;
    private BigDecimal balanceChange;
    private BigDecimal balance;
    private BigDecimal bank;
}
