package com.pth.cryptocurrencyexchange.core.exceptionhandler;

import com.pth.cryptocurrencyexchange.core.data.ApiGlobalResponse;
import com.pth.cryptocurrencyexchange.core.exceptions.ApiDataValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiDataValidationException.class})
    protected ResponseEntity<Object> handleApiValidateionException(ApiDataValidationException ex, WebRequest request) {
        ApiGlobalResponse responseObject = new ApiGlobalResponse(ex.getMessage(), ex.getErrors());

        return handleExceptionInternal(ex, responseObject, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
