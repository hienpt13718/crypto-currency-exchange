package com.pth.cryptocurrencyexchange.core.exceptions;

public class AbstractApiException extends RuntimeException {

    protected AbstractApiException(String message) {
        super(message);
    }

    protected AbstractApiException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AbstractApiException(Throwable cause) {
        super(cause);
    }
}
