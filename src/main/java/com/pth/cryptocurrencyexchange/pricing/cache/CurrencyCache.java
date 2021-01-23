package com.pth.cryptocurrencyexchange.pricing.cache;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyCache {
    private final Map<String, CurrencyCacheData> cacheDataMap = new HashMap<>();

    public void put(CurrencyCacheData cacheData) {
        synchronized (cacheDataMap) {
            cacheDataMap.put(cacheData.getCurrency(), cacheData);
        }
    }

    public CurrencyCacheData get(String key) {
        synchronized (cacheDataMap) {
            return cacheDataMap.get(key);
        }
    }

    public Collection<CurrencyCacheData> getAllCachedData() {
        synchronized (cacheDataMap) {
            return cacheDataMap.values();
        }
    }
}
