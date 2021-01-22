package com.pth.cryptocurrencyexchange.core.exceptions;

public class ApiRunTimeException extends AbstractApiException {

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
