package com.pth.cryptocurrencyexchange.pricing.schedule;

import com.pth.cryptocurrencyexchange.pricing.cache.CurrencyCache;
import com.pth.cryptocurrencyexchange.pricing.cache.CurrencyCacheData;
import com.pth.cryptocurrencyexchange.pricing.domain.SpotPriceData;
import com.pth.cryptocurrencyexchange.pricing.kafka.publisher.PricingPublisher;
import com.pth.cryptocurrencyexchange.pricing.services.AbstractProfitFactorProvider;
import com.pth.cryptocurrencyexchange.pricing.services.SpotPriceDownloader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@EnableScheduling
@Slf4j
public class PricingFluctuationsScheduler {
    private static final String MESSAGE_FORMAT = "Currency %s, Spot Price is %s, and Profit Factor is %s";
    @Autowired
    private SpotPriceDownloader spotPriceDownloader;
    @Autowired
    private AbstractProfitFactorProvider profitFactorProvider;
    @Autowired
    private PricingPublisher pricingPublisher;
    @Autowired
    private CurrencyCache currencyCache;

    @Scheduled(fixedDelayString = "${app.schedule.spotPrice.interval:1000}")
    public void updateSpotPriceAndProfitFactorAnDisplayInfoIfPricingChange() {
        float profitFactor = profitFactorProvider.getProfitFactor();

        currencyCache.getAllCachedData()
            .stream()
            .forEach(currencyCacheData -> {
                SpotPriceData spotPriceData = spotPriceDownloader
                        .getSpotPriceByCurrencyAndCacheIfNeeded(currencyCacheData.getCurrency());
                BigDecimal spotPrice = spotPriceData.getAmount();

                if (isPricingChange(currencyCacheData, spotPrice, profitFactor)) {
                    updateDataCache(currencyCacheData, spotPrice, profitFactor);
                    String sendMessage = String.format(MESSAGE_FORMAT,
                            currencyCacheData.getCurrency(), spotPrice, profitFactor);
                    pricingPublisher.sendMessage(sendMessage);
                }
            });
    }

    private void updateDataCache(CurrencyCacheData currencyCacheData,
                                 BigDecimal spotPrice, float profitFactor) {
        currencyCacheData.setSpotPrice(spotPrice);
        currencyCacheData.setProfitFactor(profitFactor);
    }

    private boolean isPricingChange(CurrencyCacheData currencyCacheData,
                                    BigDecimal spotPrice, float profitFactor) {
        if (currencyCacheData.getSpotPrice() == null
                || currencyCacheData.getProfitFactor() == null) {
            return true;
        }

        return currencyCacheData.getSpotPrice().compareTo(spotPrice) != 0
                || currencyCacheData.getProfitFactor().compareTo(profitFactor) != 0;
    }
}
