package com.vali.webservice.services;

import com.vali.webservice.dto.CurrencyHeader;
import com.vali.webservice.utils.CSVReader;
import com.vali.webservice.utils.CSVUtils;
import com.vali.webservice.utils.CurrencyValidator;
import com.vali.webservice.utils.PropertiesReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ParseCSV}.
 */
@ExtendWith(MockitoExtension.class)
public class ParseCSVTest {

    private static final String FULL_PATH = "Some Full Path";
    private static final String CURRENCY = "EUR";

    private static final double AVERAGE_ZERO = 0d;

    private static final double AVERAGE_RESULT = 12.3d;

    @Mock
    public PropertiesReader propertiesReader;

    @Mock
    public CSVReader csvReader;

    @Mock
    public CurrencyValidator currencyValidator;

    @InjectMocks
    public ParseCSV parseCSV = new ParseCSV();

    @Test
    public void getAverage_with_empty_list_should_succeed() {
        // given
        when(propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH)).thenReturn(FULL_PATH);
        when(csvReader.getCSVRecords(FULL_PATH)).thenReturn(new ArrayList<>());

        // when
        double result = parseCSV.getAverage(CURRENCY);

        // then
        verify(currencyValidator).validateCurrency(CURRENCY);
        verify(currencyValidator).validateFullPath(FULL_PATH);
        verify(propertiesReader).getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);
        verify(csvReader).getCSVRecords(FULL_PATH);
        verifyNoMoreInteractions(currencyValidator, propertiesReader, csvReader);

        assertEquals(AVERAGE_ZERO, result, CSVUtils.DELTA);
    }

    @Test
    public void getAverage_with_list_without_given_currency_should_succeed() throws IOException  {
        // given
        List<CSVRecord> list = List.of(getCSVRecord("GBP,10.2"), getCSVRecord("GBP,14.4"));

        when(propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH)).thenReturn(FULL_PATH);
        when(csvReader.getCSVRecords(FULL_PATH)).thenReturn(list);

        // when
        double result = parseCSV.getAverage(CURRENCY);

        // then
        verify(currencyValidator).validateCurrency(CURRENCY);
        verify(currencyValidator).validateFullPath(FULL_PATH);
        verify(propertiesReader).getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);
        verify(csvReader).getCSVRecords(FULL_PATH);
        verifyNoMoreInteractions(currencyValidator, propertiesReader, csvReader);

        assertEquals(AVERAGE_ZERO, result, CSVUtils.DELTA);
    }

    @Test
    public void getAverage_with_list_with_given_currency_should_succeed() throws IOException  {
        // given
        List<CSVRecord> list = List.of(getCSVRecord("EUR,10.2"), getCSVRecord("EUR,14.4"));

        when(propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH)).thenReturn(FULL_PATH);
        when(csvReader.getCSVRecords(FULL_PATH)).thenReturn(list);

        // when
        double result = parseCSV.getAverage(CURRENCY);

        // then
        verify(currencyValidator).validateCurrency(CURRENCY);
        verify(currencyValidator).validateFullPath(FULL_PATH);
        verify(propertiesReader).getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);
        verify(csvReader).getCSVRecords(FULL_PATH);
        verifyNoMoreInteractions(currencyValidator, propertiesReader, csvReader);

        assertEquals(AVERAGE_RESULT, result, CSVUtils.DELTA);
    }

    @Test
    public void getAvailableCurrencies_with_records_should_succeed() throws  IOException {
        // given
        List<CSVRecord> list = List.of(getCSVRecord("EUR,10.2"), getCSVRecord("GBP,14.4"));

        when(propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH)).thenReturn(FULL_PATH);
        when(csvReader.getCSVRecords(FULL_PATH)).thenReturn(list);

        // when
        List<String> currencies = parseCSV.getAvailableCurrencies();

        // then
        verifyNoInteractions(currencyValidator);
        verify(propertiesReader).getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);
        verify(csvReader).getCSVRecords(FULL_PATH);
        verifyNoMoreInteractions(propertiesReader, csvReader);

        assertEquals(List.of("EUR", "GBP"), currencies);
    }

    /**
     * method used for creating an CSV Record.
     * @param record String
     * @return apache CSVRecord
     * @throws IOException in case of IO error
     */
    private CSVRecord getCSVRecord(String record) throws IOException {
        List<CSVRecord> result;

        // header is required to be able to obtain a CSVRecord from a CSVParser
        CSVFormat.Builder csvFormatBuilder = CSVFormat.DEFAULT.builder();
        csvFormatBuilder.setHeader(CurrencyHeader.class);
        CSVFormat csvFormat = csvFormatBuilder.build();

        try (CSVParser parser = CSVParser.parse(new StringReader(record), csvFormat)) {
            result = parser.getRecords();
        }

        // as we do not need the header line, but we are forced to set it
        return result.get(0);
    }
}
