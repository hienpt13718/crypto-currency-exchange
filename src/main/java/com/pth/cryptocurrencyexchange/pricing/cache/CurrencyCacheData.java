package com.pth.cryptocurrencyexchange.pricing.cache;

import java.math.BigDecimal;

public class CurrencyCacheData {
    private String currency;
    private BigDecimal spotPrice;
    private Float profitFactor;

    public CurrencyCacheData(String currency) {
        this.currency = currency;
    }

    public CurrencyCacheData(String currency, BigDecimal spotPrice, Float profitFactor) {
        this.currency = currency;
        this.spotPrice = spotPrice;
        this.profitFactor = profitFactor;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(BigDecimal spotPrice) {
        this.spotPrice = spotPrice;
    }

    public Float getProfitFactor() {
        return profitFactor;
    }

    public void setProfitFactor(float profitFactor) {
        this.profitFactor = profitFactor;
    }
}
