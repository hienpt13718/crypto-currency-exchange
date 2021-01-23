package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingResponse;

public interface PricingService {
    PricingResponse getPriceHaveToPayWhenBuying(PricingRequest pricingRequest);
    PricingResponse getPriceWillReceiveWhenSelling(PricingRequest pricingRequest);
    BuyBTCResponse getAmountBTCCanBeBought(BuyBTCRequest request);
}
