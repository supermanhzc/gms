package com.taoyuan.gms.model.dto.admin;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class CardTypeRequest extends TyPageEntity {
    private int cardType;

    private String cardId;
}
