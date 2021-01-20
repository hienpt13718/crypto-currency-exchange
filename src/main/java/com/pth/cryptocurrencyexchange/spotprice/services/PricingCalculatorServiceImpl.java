package com.pth.cryptocurrencyexchange.spotprice.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingCalculatorServiceImpl implements PricingCalculatorService {

    @Override
    public BigDecimal calculate(float spotPrice, float profitFactor, int amount) {
        return BigDecimal.valueOf(spotPrice * profitFactor * amount);
    }
}
