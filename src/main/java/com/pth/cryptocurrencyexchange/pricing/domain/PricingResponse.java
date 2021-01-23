package com.pth.cryptocurrencyexchange.pricing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pth.cryptocurrencyexchange.core.serialization.BigDecimalSerializer;

import java.math.BigDecimal;

@JsonPropertyOrder({
    "id",
    "spotPrice",
    "profitFactor",
    "amount",
    "totalPrice"
})
public class PricingResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("spotPrice")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal spotPrice;
    @JsonProperty("profitFactor")
    private float profitFactor;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("totalPrice")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(BigDecimal spotPrice) {
        this.spotPrice = spotPrice;
    }

    public float getProfitFactor() {
        return profitFactor;
    }

    public void setProfitFactor(float profitFactor) {
        this.profitFactor = profitFactor;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
