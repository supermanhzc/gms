package com.taoyuan.gms.model.dto.proxy;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleStatisticStartEndRequest extends TyPageEntity {
    private LocalDate start;
    private LocalDate end;
}
