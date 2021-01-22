package com.pth.cryptocurrencyexchange.configurations;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProcessorConfiguration {
    @Bean
    public java.util.function.Consumer<KStream<String, String>> process() {
        return input ->
                input.foreach((key, value) -> {
                    System.out.println("Key: " + key + " Value: " + value);
                });
    }
}
