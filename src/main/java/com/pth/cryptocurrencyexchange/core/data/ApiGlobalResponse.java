package com.pth.cryptocurrencyexchange.core.data;

import java.util.ArrayList;
import java.util.List;

public class ApiGlobalResponse {
    private String message;
    private List<ApiParameterError> errors = new ArrayList<>();

    public ApiGlobalResponse (final String message, final List<ApiParameterError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ApiParameterError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiParameterError> errors) {
        this.errors = errors;
    }
}
