package com.pth.cryptocurrencyexchange.pricing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotPriceResponse {
    @JsonProperty("data")
    private SpotPriceData data;

    public SpotPriceData getData() {
        return data;
    }

    public void setData(SpotPriceData data) {
        this.data = data;
    }
}
