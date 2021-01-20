package com.pth.cryptocurrencyexchange.spotprice.services;

import java.math.BigDecimal;

public interface PricingCalculatorService {
    BigDecimal calculate(float spotPrice, float profitFactor, int amount);
}
