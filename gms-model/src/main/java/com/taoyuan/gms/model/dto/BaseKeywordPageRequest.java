package com.taoyuan.gms.model.dto;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class BaseKeywordPageRequest extends TyPageEntity {
    private String keyword;
}
