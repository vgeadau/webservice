package com.vali.webservice.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link PropertiesReaderTest}.
 */
@ExtendWith(MockitoExtension.class)
public class PropertiesReaderTest {

    private static final String FULL_PATH = "Some valid full path";

    @Mock
    public Environment environment;

    @InjectMocks
    PropertiesReader propertiesReader = new PropertiesReader();

    @Test
    public void getConfigValue_with_valid_propertyName_should_succeed() {
        // given
        when(environment.getProperty(CSVUtils.CURRENCIES_FULL_PATH)).thenReturn(FULL_PATH);

        // when
        String result = propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);

        // then
        assertEquals(FULL_PATH, result);
    }

    @Test
    public void getConfigValue_with_invalid_propertyName_should_succeed() {
        // given
        when(environment.getProperty(CSVUtils.CURRENCIES_FULL_PATH)).thenReturn(null);

        // when
        String result = propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);

        // then
        assertNull(result);
    }
}
