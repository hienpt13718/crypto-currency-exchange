package com.pth.cryptocurrencyexchange.configurations;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class KafkaProcessorConfiguration {
    @Bean
    public java.util.function.Consumer<KStream<String, String>> process() {
        return input ->
                input.foreach((key, value) -> {
                    log.info("Currency Exchange Info {}", value);
                });
    }
}
