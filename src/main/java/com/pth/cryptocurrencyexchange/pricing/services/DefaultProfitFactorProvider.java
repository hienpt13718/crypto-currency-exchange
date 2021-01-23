package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DefaultProfitFactorProvider extends AbstractProfitFactorProvider {
    @Value("${app.profitFactors}")
    private float[] profitFactors;

    @Override
    public float getProfitFactor() {
        int randomPosition = new Random().nextInt(profitFactors.length);
        float selectedProfitFactor = profitFactors[randomPosition];
        return selectedProfitFactor / CoreConstants.ONE_HUNDRED;
    }
}
