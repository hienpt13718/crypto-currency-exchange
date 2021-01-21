package com.pth.cryptocurrencyexchange.spotprice.api;

import com.pth.cryptocurrencyexchange.spotprice.services.SpotPriceDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/spot")
public class SpotPriceApiResource {

    @Autowired
    private SpotPriceDownloader spotPriceDownloader;

    @GetMapping
    public String getSpot(@RequestParam(value = "currency", required = false) String currency) {
//        return spotPriceDownloader.getSpotPrice(currency);
        spotPriceDownloader.sendMessage(currency);
        return "OK";
    }
}
