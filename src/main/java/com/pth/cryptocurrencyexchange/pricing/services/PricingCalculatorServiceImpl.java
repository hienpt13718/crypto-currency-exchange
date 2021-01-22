package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingDataResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.SpotPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Service
public class PricingCalculatorServiceImpl implements PricingCalculatorService {
    @Value("${app.profitFactors}")
    private float[] profitFactors;

    @Autowired
    private SpotPriceDownloader spotPriceDownloader;

    @Autowired
    private PricingCalculationValidator pricingCalculationValidator;

    @Override
    public PricingDataResponse getPricingDataResponse(PricingRequest pricingRequest) {
        pricingCalculationValidator.validatePricingCalculationInputs(pricingRequest);

        String currency = pricingRequest.getCurrency();
        int amount = pricingRequest.getAmount();
        float profitFactor = getProfitFactor();

        SpotPriceResponse.SpotPriceData spotPriceData = spotPriceDownloader.getSpotPriceByCurrency(currency);
        BigDecimal totalPrice = calculateTotalPrice(spotPriceData.getAmount(), profitFactor, amount);

        PricingDataResponse responseData = new PricingDataResponse();
        responseData.setId(UUID.randomUUID().toString());
        responseData.setAmount(amount);
        responseData.setProfitFactor(profitFactor);
        responseData.setSpotPrice(spotPriceData.getAmount());
        responseData.setTotalPrice(totalPrice);

        return responseData;
    }

    @Override
    public float getProfitFactor() {
        int randomPosition = new Random().nextInt(profitFactors.length);
        float selectedProfitFactor = profitFactors[randomPosition];
        return selectedProfitFactor / CoreConstants.ONE_HUNDRED;
    }

    @Override
    public BigDecimal calculateTotalPrice(BigDecimal spotPrice, float profitFactor, int amount) {
        return spotPrice
                .multiply(BigDecimal.valueOf(profitFactor))
                .multiply(BigDecimal.valueOf(amount));
    }
}
