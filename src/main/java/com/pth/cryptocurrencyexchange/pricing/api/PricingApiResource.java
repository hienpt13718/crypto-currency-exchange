package com.pth.cryptocurrencyexchange.pricing.api;

import com.pth.cryptocurrencyexchange.pricing.domain.PricingDataResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;
import com.pth.cryptocurrencyexchange.pricing.kafka.publisher.PricingPublisher;
import com.pth.cryptocurrencyexchange.pricing.services.PricingCalculatorService;
import com.pth.cryptocurrencyexchange.pricing.services.PricingCalculatorServiceImpl;
import com.pth.cryptocurrencyexchange.pricing.services.SpotPriceDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/spot")
public class PricingApiResource {

    @Autowired
    private PricingPublisher pricingPublisher;

    @Autowired
    private PricingCalculatorService pricingCalculatorService;

    @Autowired
    private SpotPriceDownloader spotPriceDownloader;

    @GetMapping
    public String getSpot(@RequestParam(value = "currency", required = false) String currency) {
        spotPriceDownloader.testPricingDislay();
        return "OK";
    }

    @PostMapping("/price")
    public PricingDataResponse getPriceNeedToBy(@RequestBody PricingRequest pricingRequest) {
        return pricingCalculatorService.getPricingDataResponse(pricingRequest);
    }
}
