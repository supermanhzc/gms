package com.taoyuan.gms.model.dto.admin.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProxyRequest extends AccountBaseRequest {
    private String name;
    private BigDecimal distribMoney;
}
