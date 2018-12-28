package com.taoyuan.gms.model.dto.admin.card;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class CardPwdInventoryResquest extends TyPageEntity {
    private String keyword;

    private int cardType;
}
