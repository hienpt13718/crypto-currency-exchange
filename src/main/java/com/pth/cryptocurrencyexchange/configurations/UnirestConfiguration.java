package com.pth.cryptocurrencyexchange.configurations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class UnirestConfiguration {

    @Autowired
    com.fasterxml.jackson.databind.ObjectMapper mapper;

    @PostConstruct
    private void postConstruct() {
        Unirest.setObjectMapper(new ObjectMapper() {
            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (JsonProcessingException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
        });
    }
}
