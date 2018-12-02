package com.taoyuan.gms.model.dto.admin.charts;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class ChartsRequest extends TyPageEntity {
    private int count;
}
