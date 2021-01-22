package com.pth.cryptocurrencyexchange.pricing.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pth.cryptocurrencyexchange.core.exceptions.ApiRunTimeException;
import com.pth.cryptocurrencyexchange.pricing.domain.SpotPriceResponse;
import com.pth.cryptocurrencyexchange.pricing.kafka.publisher.PricingPublisher;
import com.pth.cryptocurrencyexchange.util.ThreadLocalContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class SpotPriceDownloader {
    @Value("${app.cryptoCurrencyExchange.spotPriceUrl}")
    private String spotPriceUrl;
    @Value("${app.cryptoCurrencyExchange.defaultCurrency}")
    private String defaultCurrency;

    @Autowired
    private PricingPublisher pricingPublisher;

    @PostConstruct
    public void postConstruct() {
        ThreadLocalContextUtil.setCurrency(defaultCurrency);
    }

    @Scheduled(fixedDelayString = "${app.schedule.spotPrice.interval:1000}")
    public void getSpotPriceByCurrencyScheduled() {
//        getSpotPriceByCurrency()
        log.info("Scheduled");
    }

    public void testPricingDislay() {
        SpotPriceResponse.SpotPriceData spotPriceData = getSpotPriceByCurrency(defaultCurrency);
        pricingPublisher.sendMessage(String.format("Spot price = %s, Profit factor = 123", spotPriceData.getAmount()));
    }

    public SpotPriceResponse.SpotPriceData getSpotPriceByCurrency(String currency) {
        if (StringUtils.isBlank(currency)) {
            currency = defaultCurrency;
        }

        final String urlWithCurrencyParam = String.format(spotPriceUrl, currency);
        try {
            HttpResponse<SpotPriceResponse> response
                    = Unirest.get(urlWithCurrencyParam)
                    .asObject(SpotPriceResponse.class);

            return response.getBody().getData();
        } catch (UnirestException e) {
            throw new ApiRunTimeException(e.getMessage());
        }
    }

}
