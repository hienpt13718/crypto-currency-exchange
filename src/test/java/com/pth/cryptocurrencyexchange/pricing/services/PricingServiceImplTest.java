package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.pricing.domain.*;
import com.pth.cryptocurrencyexchange.pricing.handler.PricingCalculateHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class PricingServiceImplTest {

    @InjectMocks
    private PricingService pricingService = new PricingServiceImpl();
    @Mock
    private SpotPriceDownloader spotPriceDownloader;
    @Mock
    private PricingCalculateHandler pricingCalculator;
    @Mock
    private AbstractProfitFactorProvider profitFactorProvider;
    @Mock
    private PricingCalculationValidator pricingCalculationValidator;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.doNothing()
                .when(pricingCalculationValidator).validatePricingCalculationInputs(Mockito.any());

        SpotPriceData response = createSpotPriceDummyData();
        Mockito.doReturn(response)
                .when(spotPriceDownloader).getSpotPriceByCurrencyAndCacheIfNeeded(Mockito.anyString());
    }

    @Test
    void getPriceHaveToPayWhenBuying() {
        PricingResponse actualPricingResponse
                = pricingService.getPriceHaveToPayWhenBuying(createDummyPricingRequest());
        assertNotNull(actualPricingResponse);
    }

    @Test
    void getPriceWillReceiveWhenSelling() {
        PricingResponse actualPricingResponse
                = pricingService.getPriceWillReceiveWhenSelling(createDummyPricingRequest());
        assertNotNull(actualPricingResponse);
    }

    @Test
    void getAmountBTCCanBeBought() {
        BuyBTCResponse actualPricingResponse
                = pricingService.getAmountBTCCanBeBought(createDummyBuyBTCRequest());
        assertNotNull(actualPricingResponse);
    }

    private PricingResponse createDummyPricingResponse() {
        PricingResponse responseData = new PricingResponse();
        responseData.setId(UUID.randomUUID().toString());
        responseData.setAmount(2);
        responseData.setSpotPrice(BigDecimal.valueOf(45000));
        responseData.setTotalPrice(BigDecimal.valueOf(90000));

        return responseData;
    }

    private SpotPriceData createSpotPriceDummyData() {
        SpotPriceData spotPriceData = new SpotPriceData();
        spotPriceData.setAmount(BigDecimal.valueOf(45000));
        spotPriceData.setBase("BTC");
        spotPriceData.setCurrency("NZD");
        return spotPriceData;
    }

    private BuyBTCResponse createDummyBuyBTCResponse() {
        return new BuyBTCResponse("NZD", BigDecimal.valueOf(45000), BigDecimal.ONE);
    }

    private BuyBTCRequest createDummyBuyBTCRequest() {
        BuyBTCRequest buyBTCRequest = new BuyBTCRequest();
        buyBTCRequest.setCurrency("NZD");
        buyBTCRequest.setAmountOfMoney(BigDecimal.valueOf(45000));

        return buyBTCRequest;
    }

    private PricingRequest createDummyPricingRequest() {
        PricingRequest request = new PricingRequest();
        request.setAmount(3);
        request.setCurrency("NZD");

        return request;
    }
}