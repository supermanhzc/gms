package com.taoyuan.gms.model.dto;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class BaseIdStatusPageRequest extends TyPageEntity {
    private Long id;

    private int status;
}
