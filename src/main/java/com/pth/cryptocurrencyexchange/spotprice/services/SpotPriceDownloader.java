package com.pth.cryptocurrencyexchange.spotprice.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pth.cryptocurrencyexchange.exceptions.ApiRunTimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class SpotPriceDownloader {
    public static String CURRENCY; //TODO: How to use for multiple request with different currency?
    @Value("${app.cryptoCurrencyExchange.spotPriceUrl}")
    private String spotPriceUrl;
    @Value("${app.cryptoCurrencyExchange.defaultCurrency}")
    private String defaultCurrency;


    @PostConstruct
    public void postConstruct() {
        CURRENCY = defaultCurrency;
    }

    @Scheduled(fixedDelayString = "${app.schedule.spotPrice.interval:1000}")
    public void getSpotPriceByCurrencyScheduled() {
//        getSpotPriceByCurrency()
        log.info("Scheduled");
    }

    public String getSpotPriceByCurrency(String currency) {
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


    // TODO: Move to the publisher class
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>>  senListener = kafkaTemplate.send("words", message);
        senListener.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Fail when sending message", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("Send message={} successfully, with offset={}, key-value{}, data={}", message,
                        stringStringSendResult.getRecordMetadata().offset(),
                        stringStringSendResult.getProducerRecord().key() + "-" + stringStringSendResult.getProducerRecord().value(),
                        stringStringSendResult.getRecordMetadata());
            }
        });
    }


}
