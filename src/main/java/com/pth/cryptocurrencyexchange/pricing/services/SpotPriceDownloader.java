package com.pth.cryptocurrencyexchange.pricing.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import com.pth.cryptocurrencyexchange.core.exceptions.ApiRunTimeException;
import com.pth.cryptocurrencyexchange.pricing.cache.CurrencyCache;
import com.pth.cryptocurrencyexchange.pricing.cache.CurrencyCacheData;
import com.pth.cryptocurrencyexchange.pricing.domain.SpotPriceResponse;
import com.pth.cryptocurrencyexchange.util.MyStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Objects;

@Service
@Slf4j
public class SpotPriceDownloader {
    @Value("${app.cryptoCurrencyExchange.spotPriceUrl}")
    private String spotPriceUrl;
    @Value("${app.cryptoCurrencyExchange.defaultCurrency}")
    private String defaultCurrency;

    @Autowired
    private CurrencyCache currencyCache;

    @PostConstruct
    public void init() {
        cacheCurrencyIfHasNewValue(defaultCurrency);
    }

    public SpotPriceResponse.SpotPriceData getSpotPriceByCurrencyAndCacheIfNeeded(String currency) {
        String availableCurrency = MyStringUtil.stripAndDefaultString(currency, defaultCurrency);

        final String urlWithCurrencyParam = String.format(spotPriceUrl, availableCurrency);
        try {
            HttpResponse<SpotPriceResponse> response
                    = Unirest.get(urlWithCurrencyParam)
                    .asObject(SpotPriceResponse.class);

            if (response.getStatus() != HttpStatus.OK.value()) {
                throw new ApiRunTimeException(String.format(CoreConstants.INVALID_CURRENCY_ERRORS_MESSAGE, availableCurrency));
            }

            cacheCurrencyIfHasNewValue(availableCurrency);
            return response.getBody().getData();
        } catch (UnirestException e) {
            throw new ApiRunTimeException(e.getMessage());
        }
    }

    private void cacheCurrencyIfHasNewValue(String currency) {
        CurrencyCacheData cacheData = currencyCache.get(currency);
        if (Objects.nonNull(cacheData)) {
            return;
        }

        currencyCache.put(new CurrencyCacheData(currency));
    }
}
