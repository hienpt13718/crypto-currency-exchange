package com.pth.cryptocurrencyexchange.pricing.domain;

import java.math.BigDecimal;

public class BuyBTCResponse {
    private String currency;
    private BigDecimal spotPrice;
    private BigDecimal amountBTC;

    public BuyBTCResponse(String currency, BigDecimal spotPrice, BigDecimal amountBTC) {
        this.currency = currency;
        this.spotPrice = spotPrice;
        this.amountBTC = amountBTC;
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

    public BigDecimal getAmountBTC() {
        return amountBTC;
    }

    public void setAmountBTC(BigDecimal amountBTC) {
        this.amountBTC = amountBTC;
    }
}
