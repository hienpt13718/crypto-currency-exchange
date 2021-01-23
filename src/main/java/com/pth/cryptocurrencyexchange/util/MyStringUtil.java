package com.pth.cryptocurrencyexchange.util;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtil {
    private MyStringUtil() {}

    public static String stripAndDefaultString(String str, String strDefault) {
        str = StringUtils.strip(str);
        if (StringUtils.isBlank(str)) {
            return strDefault;
        }
        return str;
    }
}
