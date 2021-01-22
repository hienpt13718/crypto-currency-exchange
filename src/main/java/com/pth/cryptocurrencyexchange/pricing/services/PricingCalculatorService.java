package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.pricing.domain.PricingDataResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;

import java.math.BigDecimal;

public interface PricingCalculatorService {
    PricingDataResponse getPricingDataResponse(PricingRequest pricingRequest);

    BigDecimal calculateTotalPrice(BigDecimal spotPrice, float profitFactor, int amount);

    float getProfitFactor();
}
