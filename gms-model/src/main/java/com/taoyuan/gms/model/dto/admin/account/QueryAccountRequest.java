package com.taoyuan.gms.model.dto.admin.account;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

import java.util.List;

@Data
public class QueryAccountRequest extends TyPageEntity {
    private String queryKey;
    private List<Integer> types;
}
