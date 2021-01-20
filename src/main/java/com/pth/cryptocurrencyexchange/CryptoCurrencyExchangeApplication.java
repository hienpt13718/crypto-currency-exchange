package com.pth.cryptocurrencyexchange;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class CryptoCurrencyExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoCurrencyExchangeApplication.class, args);
	}


//	@Bean
//	public Consumer<KStream<String, String>> process() {
//		return input ->
//				input.foreach((key, value) -> {
//					System.out.println("Key " + key + " value: " + value);
//				});
//	}
}
