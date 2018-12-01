package com.taoyuan.gms.common.util;

import com.taoyuan.framework.common.exception.ValidateException;

import java.math.BigDecimal;

public class CardUtil {

    public static String getCardHead(int cardType) {
        String cardHead = null;
        switch (cardType) {
            case 1:
                cardHead = "10y";
                break;
            case 2:
                cardHead = "20y";
                break;
            case 3:
                cardHead = "30y";
                break;
            case 4:
                cardHead = "reddiamond";
                break;
            default:
                throw new ValidateException("不支持的卡类型。");
        }
        return cardHead;
    }

    public static BigDecimal getMoney(int cardType) {
        switch (cardType) {
            case 1:
                return BigDecimal.valueOf(10);
            case 2:
                return BigDecimal.valueOf(20);
            case 3:
                return BigDecimal.valueOf(30);
            case 4:
                return BigDecimal.valueOf(100);
            default:
                break;
        }

        return BigDecimal.ZERO;
    }
}
