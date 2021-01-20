package com.pth.cryptocurrencyexchange.spotprice.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pth.cryptocurrencyexchange.exceptions.ApiRunTimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpotPriceDownloader {

    @Value("${cce.prices.spot.url}")
    private String spotPriceUrl;
    @Value("${cce.prices.spot.defaultCurrency}")
    private String defaultCurrency;

    public String getSpotPrice(String currency) {
        if (StringUtils.isBlank(currency)) {
            currency = defaultCurrency;
        }

        final String urlWithCurrencyParam = String.format(spotPriceUrl, currency);
        try {
            HttpResponse<String> response = Unirest.get(urlWithCurrencyParam).asString();
            return response.getBody();
        } catch (UnirestException e) {
            throw new ApiRunTimeException(e.getMessage());
        }
    }


}
