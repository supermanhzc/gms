package com.taoyuan.gms.model.dto.admin.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class QueryAccountBalanceRequest extends TyPageEntity{
    private Long id;
    private Integer type;

}
