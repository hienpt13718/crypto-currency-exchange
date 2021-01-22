package com.pth.cryptocurrencyexchange.pricing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

public class SpotPriceResponse {
    @JsonProperty("data")
    private SpotPriceData data;

    public SpotPriceData getData() {
        return data;
    }

    public void setData(SpotPriceData data) {
        this.data = data;
    }

    @JsonPropertyOrder({
            "base",
            "currency",
            "amount"
    })
    public class SpotPriceData {
        @JsonProperty("base")
        private String base;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("amount")
        private BigDecimal amount;

        public SpotPriceData() {}

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }
}
