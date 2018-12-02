package com.taoyuan.gms.model.dto.admin.statistic;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class SaleStatisticsRequest extends TyPageEntity {

    private String start;

    private String end;

    private String proxyName;
}
