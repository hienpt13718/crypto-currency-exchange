package com.pth.cryptocurrencyexchange.pricing.kafka.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class PricingPublisher {
    @Value("${spring.cloud.stream.function.bindings.process-in-0}")
    private String topicName;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> senListener = kafkaTemplate.send(topicName, message);
        senListener.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Fail when sending message", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.debug("Message is sent successfully");
            }
        });
    }
}
