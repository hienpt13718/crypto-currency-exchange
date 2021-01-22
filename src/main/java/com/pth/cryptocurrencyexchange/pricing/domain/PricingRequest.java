package com.pth.cryptocurrencyexchange.pricing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pth.cryptocurrencyexchange.pricing.PricingConstants;

public class PricingRequest {
    @JsonProperty(PricingConstants.PRICING_REQUEST_CURRENCY_PROPERTY)
    private String currency;
    @JsonProperty(PricingConstants.PRICING_REQUEST_AMOUNT_PROPERTY)
    private int amount;

    public PricingRequest() {}

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
