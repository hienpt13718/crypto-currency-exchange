package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.SpotPriceResponse.SpotPriceData;
import com.pth.cryptocurrencyexchange.pricing.handler.PricingCalculateHandler;
import com.pth.cryptocurrencyexchange.util.MyStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PricingServiceImpl implements PricingService {
    @Value("${app.cryptoCurrencyExchange.defaultCurrency}")
    private String defaultCurrency;

    @Autowired
    private SpotPriceDownloader spotPriceDownloader;
    @Autowired
    private PricingCalculateHandler pricingCalculator;
    @Autowired
    private AbstractProfitFactorProvider profitFactorProvider;
    @Autowired
    private PricingCalculationValidator pricingCalculationValidator;

    @Override
    public PricingResponse getPriceHaveToPayWhenBuying(PricingRequest pricingRequest) {
        pricingCalculationValidator.validatePricingCalculationInputs(pricingRequest);
        String currency = pricingRequest.getCurrency();
        int amount = pricingRequest.getAmount();

        SpotPriceData spotPriceData = spotPriceDownloader
                .getSpotPriceByCurrencyAndCacheIfNeeded(currency);
        BigDecimal spotPrice = spotPriceData.getAmount();
        BigDecimal totalPrice = spotPrice.multiply(BigDecimal.valueOf(amount));

        PricingResponse responseData = new PricingResponse();
        responseData.setId(UUID.randomUUID().toString());
        responseData.setAmount(amount);
        responseData.setSpotPrice(spotPriceData.getAmount());
        responseData.setTotalPrice(totalPrice);

        return responseData;
    }

    @Override
    public PricingResponse getPriceWillReceiveWhenSelling(PricingRequest pricingRequest) {
        pricingCalculationValidator.validatePricingCalculationInputs(pricingRequest);

        String currency = pricingRequest.getCurrency();
        int amount = pricingRequest.getAmount();
        float profitFactor = profitFactorProvider.getProfitFactor();

        SpotPriceData spotPriceData = spotPriceDownloader.getSpotPriceByCurrencyAndCacheIfNeeded(currency);
        BigDecimal totalPrice = pricingCalculator
                .calculateTotalPriceWithProfitMargin(spotPriceData.getAmount(),profitFactor, amount);

        PricingResponse responseData = new PricingResponse();
        responseData.setId(UUID.randomUUID().toString());
        responseData.setAmount(amount);
        responseData.setProfitFactor(profitFactor);
        responseData.setSpotPrice(spotPriceData.getAmount());
        responseData.setTotalPrice(totalPrice);

        return responseData;
    }

    @Override
    public BuyBTCResponse getAmountBTCCanBeBought(BuyBTCRequest request) {
        pricingCalculationValidator.validateAmountCanBeBought(request);

        String currency = MyStringUtil.stripAndDefaultString(request.getCurrency(), defaultCurrency);
        SpotPriceData spotPriceData = spotPriceDownloader
                .getSpotPriceByCurrencyAndCacheIfNeeded(currency);

        BigDecimal spotPrice = spotPriceData.getAmount();
        BigDecimal amountBTCCanBeBought = pricingCalculator
                .getAmountBTCCanBeBought(spotPrice, request.getAmountOfMoney());

        return new BuyBTCResponse(currency, spotPrice, amountBTCCanBeBought);
    }
}
