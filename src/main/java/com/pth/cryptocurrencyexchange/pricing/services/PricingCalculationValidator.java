package com.pth.cryptocurrencyexchange.pricing.services;

import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import com.pth.cryptocurrencyexchange.core.data.ApiParameterError;
import com.pth.cryptocurrencyexchange.core.exceptions.ApiDataValidationException;
import com.pth.cryptocurrencyexchange.pricing.PricingConstants;
import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCRequest;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class PricingCalculationValidator {

    public void validatePricingCalculationInputs(PricingRequest pricingRequest) {
        int amount = pricingRequest.getAmount();
        List<ApiParameterError> errors = new ArrayList<>();

        if (amount <= CoreConstants.ZERO || amount > PricingConstants.MAX_ALLOWED_SPOT_PRICE) {
            ApiParameterError parameterError
                    = new ApiParameterError(PricingConstants.PRICING_REQUEST_AMOUNT_PROPERTY,
                    pricingRequest.getAmount(),
                    PricingConstants.VALIDATION_MESSAGE_INVALID_AMOUNT);
            errors.add(parameterError);
        }

        throwExceptionIfValidationErrorsExist(errors);
    }

    public void validateAmountCanBeBought(BuyBTCRequest inputData) {
        List<ApiParameterError> errors = new ArrayList<>();
        String currency = inputData.getCurrency();
        BigDecimal amountOfMoney = inputData.getAmountOfMoney();

        if (amountOfMoney == null || amountOfMoney.compareTo(BigDecimal.ZERO) <= 0) {
            ApiParameterError parameterError
                    = new ApiParameterError(PricingConstants.BUYING_BTC_AMOUNT_OF_MONEY_PROPERTY,
                    currency,
                    PricingConstants.VALIDATION_MESSAGE_INVALID_MONEY);
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
