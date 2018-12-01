package com.taoyuan.gms.model.dto.admin;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class CardPasswordRequest extends TyPageEntity {

    private String keyword;

    private int cardType;

    private int status;

    private String owner;

    private Long createUser;
}
