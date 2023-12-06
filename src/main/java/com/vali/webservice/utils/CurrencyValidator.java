package com.vali.webservice.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Class validator.
 */
@Component
public class CurrencyValidator {

    private static final List<String> allowedCurrencies = List.of("EUR", "GBP", "USD", "CHF");

    /**
     * Method that checks if the currency is supported.
     * @param currency String
     * @throws IllegalArgumentException when the currency is not supported.
     */
    public void validateCurrency(String currency) {
        if (!allowedCurrencies.contains(currency)) {
            throw new IllegalArgumentException(CSVUtils.INVALID_CURRENCY_ERROR);
        }
    }

    /**
     * Method that validates full path obtained from property file.
     * @param fullPath String
     * @throws IllegalStateException if the property is null.
     */
    public void validateFullPath(String fullPath) {
        if (Objects.isNull(fullPath)) {
            throw new IllegalStateException(CSVUtils.INVALID_CONFIGURATION_ERROR);
        }
    }
}
