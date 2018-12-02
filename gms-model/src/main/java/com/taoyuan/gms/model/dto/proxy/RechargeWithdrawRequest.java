package com.taoyuan.gms.model.dto.proxy;

import com.taoyuan.framework.common.entity.TyBaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RechargeWithdrawRequest extends TyBaseEntity {
    //类型，1充值，2提现，必填
    private int type;

    //充值或者提现金额，必填
    private BigDecimal money;
}
