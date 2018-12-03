package com.taoyuan.gms.model.entity.site.account;

import com.taoyuan.framework.common.entity.TyBaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 金豆
 */
@Data
public class GoldEntity extends TyBaseEntity {
    private Long id;

    private Long memberId;

    private BigDecimal gold;

    private Date time;
}
