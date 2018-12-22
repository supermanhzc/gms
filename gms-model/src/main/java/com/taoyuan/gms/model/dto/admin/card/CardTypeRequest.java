package com.taoyuan.gms.model.dto.admin.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class CardTypeRequest extends TyPageEntity {

    @JsonProperty(value = "cardType")
    private int cardType;

    @JsonProperty(value = "cardId")
    private String cardId;
}
