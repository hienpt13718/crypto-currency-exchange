package com.pth.cryptocurrencyexchange.core.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonPropertyOrder({
        "parameterName",
        "value",
        "errorMessage"
})
public class ApiParameterError {
    @JsonProperty("parameterName")
    private String parameterName;
    @JsonProperty("value")
    @JsonSerialize(using = ToStringSerializer.class)
    private final Object value;
    @JsonProperty("errorMessage")
    private String errorMessage;

    public ApiParameterError(String parameterName, Object value, String errorMessage) {
        this.parameterName = parameterName;
        this.value = value;
        this.errorMessage = errorMessage;
    }
}
