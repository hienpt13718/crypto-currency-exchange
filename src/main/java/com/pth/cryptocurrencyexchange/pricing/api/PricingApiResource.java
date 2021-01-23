package com.pth.cryptocurrencyexchange.pricing.api;

import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingResponse;
import com.pth.cryptocurrencyexchange.pricing.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class PricingApiResource {
    @Autowired
    private PricingService pricingService;

    @GetMapping("/ping")
    public String ping() {
        return CoreConstants.PING_SUCCESS_MESSAGE;
    }

    @GetMapping("/buy")
    public PricingResponse getPriceHaveToPayWhenBuying(@RequestBody PricingRequest pricingRequest) {
        return pricingService.getPriceHaveToPayWhenBuying(pricingRequest);
    }

    @GetMapping("/sell")
    public PricingResponse getPriceWillReceiveWhenSelling(@RequestBody PricingRequest pricingRequest) {
        return pricingService.getPriceWillReceiveWhenSelling(pricingRequest);
    }

    @GetMapping("/getAmountCanBeBought")
    public BuyBTCResponse getAmountBCTCanBy(@RequestBody BuyBTCRequest amountBTCRequest) {
        return pricingService.getAmountBTCCanBeBought(amountBTCRequest);
    }
}
