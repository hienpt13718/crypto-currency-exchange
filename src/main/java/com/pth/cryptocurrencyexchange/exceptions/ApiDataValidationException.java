package com.pth.cryptocurrencyexchange.exceptions;

public class ApiDataValidationException extends AbstractApiExeption {

    public ApiDataValidationException() {
    }

    public ApiDataValidationException(String message) {
        super(message);
    }

    public ApiDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiDataValidationException(Throwable cause) {
        super(cause);
    }
}
