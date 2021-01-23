package com.pth.cryptocurrencyexchange.pricing.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class PricingCalculateHandler {

    public BigDecimal calculateTotalPriceWithProfitMargin(BigDecimal spotPrice, float profitFactor, int amount) {
        BigDecimal price = calculateCurrentPrice(spotPrice, amount);
        BigDecimal profitMargin = calculateProfitMargin(spotPrice, profitFactor, amount);

        return price.add(profitMargin);
    }

    public BigDecimal getAmountBTCCanBeBought(BigDecimal spotPrice, BigDecimal amountOfMoney) {
        return amountOfMoney.divide(spotPrice, 5, RoundingMode.HALF_UP);
    }

    protected BigDecimal calculateCurrentPrice(final BigDecimal spotPrice, final int amount) {
        return spotPrice.multiply(BigDecimal.valueOf(amount));
    }

    protected BigDecimal calculateProfitMargin(final BigDecimal spotPrice,
                                               final float profitFactor, final int amount) {
        return spotPrice
                .multiply(BigDecimal.valueOf(profitFactor))
                .multiply(BigDecimal.valueOf(amount));
    }
}
