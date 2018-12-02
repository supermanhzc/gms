package com.taoyuan.gms.model.dto.admin.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateAccountBalanceRequest {
    private Long userId;
    private Integer type;
    private String descp;
    private BigDecimal balanceChange;
    private BigDecimal balance;
    private BigDecimal bank;
}
