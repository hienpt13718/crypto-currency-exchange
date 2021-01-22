package com.pth.cryptocurrencyexchange.core.exceptions;

import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import com.pth.cryptocurrencyexchange.core.data.ApiParameterError;

import java.util.List;

public class ApiDataValidationException extends AbstractApiException {
    private final List<ApiParameterError> errors;


    public ApiDataValidationException(final List<ApiParameterError> errors) {
        super(CoreConstants.VALIDATION_ERRORS_EXIST_MESSAGE);
        this.errors = errors;
    }

    public ApiDataValidationException(final List<ApiParameterError> errors, Throwable cause) {
        super(CoreConstants.VALIDATION_ERRORS_EXIST_MESSAGE, cause);
        this.errors = errors;
    }

    public List<ApiParameterError> getErrors() {
        return errors;
    }
}
