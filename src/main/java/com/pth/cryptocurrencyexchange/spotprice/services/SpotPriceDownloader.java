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
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class SpotPriceDownloader {

    @Value("${app.cryptoCurrencyExchange.spotPriceUrl}")
    private String spotPriceUrl;
    @Value("${app.cryptoCurrencyExchange.defaultCurrency}")
    private String defaultCurrency;

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
