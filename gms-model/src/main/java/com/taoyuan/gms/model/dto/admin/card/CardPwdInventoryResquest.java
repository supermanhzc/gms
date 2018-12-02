package com.taoyuan.gms.model.dto.admin.card;

import com.taoyuan.framework.common.entity.TyPageEntity;
import lombok.Data;

@Data
public class CardPwdInventoryResquest extends TyPageEntity {
    private String keyword;

    private int cardType;
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }
//
//    public int getCardType() {
//        return cardType;
//    }
//
//    public void setCardType(int cardType) {
//        this.cardType = cardType;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CardPwdInventoryResquest)) return false;
//        if (!super.equals(o)) return false;
//        CardPwdInventoryResquest that = (CardPwdInventoryResquest) o;
//        return getCardType() == that.getCardType() &&
//                Objects.equals(getKeyword(), that.getKeyword());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), getKeyword(), getCardType());
//    }
}
