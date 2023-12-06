package com.vali.webservice.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CurrencyValidator}.
 */
@ExtendWith(MockitoExtension.class)
public class CurrencyValidatorTest {

    private static final String FULL_PATH = "Some valid full path";
    CurrencyValidator currencyValidator = new CurrencyValidator();

    @Test
    public void validateCurrency_should_fail_for_unsupported_currency() {
        // given
        String unsupportedCurrency = "RON";

        // when
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> currencyValidator.validateCurrency(unsupportedCurrency));

        String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(CSVUtils.INVALID_CURRENCY_ERROR));
    }

    @Test
    public void validateCurrency_should_succeed_for_supported_currency() {
        // given
        String supportedCurrency = "EUR";
        boolean errorFound = false;

        // when
        try {
            currencyValidator.validateCurrency(supportedCurrency);
        } catch (Exception exception) {
            errorFound = true;
        }

        // then
        assertFalse(errorFound);
    }

    @Test
    public void validateFullPath_should_succeed() {
        // given
        boolean errorFound = false;

        // when
        try {
            currencyValidator.validateFullPath(FULL_PATH);
        } catch (Exception exception) {
            errorFound = true;
        }

        // then
        assertFalse(errorFound);
    }

    @Test
    public void validateFullPath_should_fail_for_null_path() {
        // given

        // when
        Exception exception = assertThrows(IllegalStateException.class,
                () -> currencyValidator.validateFullPath(null));

        String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(CSVUtils.INVALID_CONFIGURATION_ERROR));
    }
}
