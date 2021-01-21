package com.pth.cryptocurrencyexchange.exceptions;

public class AbstractApiExeption extends RuntimeException {

    protected AbstractApiExeption() {
        super();
    }

    protected AbstractApiExeption(String message) {
        super(message);
    }

    protected AbstractApiExeption(String message, Throwable cause) {
        super(message, cause);
    }

    protected AbstractApiExeption(Throwable cause) {
        super(cause);
    }
}
