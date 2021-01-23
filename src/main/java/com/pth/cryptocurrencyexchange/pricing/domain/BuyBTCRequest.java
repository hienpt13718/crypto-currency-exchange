package com.pth.cryptocurrencyexchange.pricing.domain;

import java.math.BigDecimal;

public class BuyBTCRequest {
    private String currency;
    private BigDecimal amountOfMoney;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(BigDecimal amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}
