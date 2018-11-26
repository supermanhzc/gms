package com.taoyuan.gms.common.util;

import java.util.regex.Pattern;

/**
 * 字符创工具类
 */
public class StringUtil {

    /**
     * 判断字符创是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
