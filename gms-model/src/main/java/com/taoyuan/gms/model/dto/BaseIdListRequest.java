package com.taoyuan.gms.model.dto;

import com.taoyuan.framework.common.entity.TyBaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class BaseIdListRequest extends TyBaseEntity {
    private List<Long> idList;
}
