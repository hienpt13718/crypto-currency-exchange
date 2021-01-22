package com.pth.cryptocurrencyexchange.util;

public class ThreadLocalContextUtil {
    private ThreadLocalContextUtil() {}

    private static final ThreadLocal<String> currencyContext = new ThreadLocal<>();

    public static void setCurrency(String currency) {
        currencyContext.set(currency);
    }

    public static String getCurrency() {
        return currencyContext.get();
    }
}
