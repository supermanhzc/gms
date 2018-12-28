package com.taoyuan.gms.model.dto.proxy;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class ProxyOperNameTypeRequest extends TyPageEntity {
    private String proxyName;
    private int type;
}
