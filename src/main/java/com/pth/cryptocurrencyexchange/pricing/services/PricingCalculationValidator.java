package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import com.pth.cryptocurrencyexchange.core.data.ApiParameterError;
import com.pth.cryptocurrencyexchange.core.exceptions.ApiDataValidationException;
import com.pth.cryptocurrencyexchange.pricing.PricingConstants;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PricingCalculationValidator {

    public void validatePricingCalculationInputs(PricingRequest pricingRequest) {
        int amount = pricingRequest.getAmount();
        List<ApiParameterError> errors = new ArrayList<>();

        if (amount <= CoreConstants.ZERO || amount > PricingConstants.MAX_ALLOWED_SPOT_PRICE) {
            ApiParameterError parameterError = new ApiParameterError(PricingConstants.PRICING_REQUEST_AMOUNT_PROPERTY,
                    pricingRequest.getAmount(),
                    PricingConstants.VALIDATION_MESSAGE_INVALID_AMOUNT);

            errors.add(parameterError);
        }

        throwExceptionIfValidationErrorsExist(errors);
    }

    private void throwExceptionIfValidationErrorsExist(List<ApiParameterError> errors) {
        if (!errors.isEmpty()) {
            throw new ApiDataValidationException(errors);
        }
    }

}
