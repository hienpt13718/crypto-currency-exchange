package com.pth.cryptocurrencyexchange.pricing;

import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;

public class PricingConstants {
    private PricingConstants() {}

    public static final int MAX_ALLOWED_AMOUNT = Integer.MAX_VALUE;
    public static final int MAX_ALLOWED_SPOT_PRICE = Integer.MAX_VALUE;

    /*Pricing request json properties*/
    public static final String PRICING_REQUEST_CURRENCY_PROPERTY = "currency";
    public static final String PRICING_REQUEST_AMOUNT_PROPERTY = "amount";

    /*Validation messages*/
    public static final String VALIDATION_MESSAGE_INVALID_AMOUNT = "Amount must be greater than 0 and less than " + MAX_ALLOWED_AMOUNT;
    public static final String VALIDATION_MESSAGE_INVALID_PROFIIT_FACTOR = "Profit factor must be greater than 0 and less than " + CoreConstants.ONE_HUNDRED;
    public static final String VALIDATION_MESSAGE_INVALID_SPOT_PRICE = "Spot price must be greater than 0 and less than " + MAX_ALLOWED_SPOT_PRICE;
}
