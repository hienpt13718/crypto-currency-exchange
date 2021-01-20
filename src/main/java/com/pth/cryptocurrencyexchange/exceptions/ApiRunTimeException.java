package com.pth.cryptocurrencyexchange.exceptions;

public class ApiRunTimeException extends RuntimeException {

    public ApiRunTimeException() {
    }

    public ApiRunTimeException(String message) {
        super(message);
    }

    public ApiRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRunTimeException(Throwable cause) {
        super(cause);
    }
}
