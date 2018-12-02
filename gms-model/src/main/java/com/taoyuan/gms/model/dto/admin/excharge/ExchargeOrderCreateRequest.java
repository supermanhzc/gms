package com.taoyuan.gms.model.dto.admin.excharge;

import com.taoyuan.framework.common.entity.TyBaseEntity;
import lombok.Data;

@Data
public class ExchargeOrderCreateRequest extends TyBaseEntity {

    //奖品名称，必填
    private String prizeName;

    //兑换数量
    private int exchangeNum;
}
