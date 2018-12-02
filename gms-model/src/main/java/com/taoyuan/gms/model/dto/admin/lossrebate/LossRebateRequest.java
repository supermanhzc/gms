package com.taoyuan.gms.model.dto.admin.lossrebate;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class LossRebateRequest extends TyPageEntity {

    private Long id;

    private int status;
}
