package com.taoyuan.gms.model.dto.admin.statistic;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class MemberLoginRequest extends TyPageEntity {

    private String keyword;

    private int status;
}
