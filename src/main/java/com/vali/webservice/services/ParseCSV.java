package com.vali.webservice.services;

import com.vali.webservice.dto.CurrencyHeader;
import com.vali.webservice.utils.CSVReader;
import com.vali.webservice.utils.CSVUtils;
import com.vali.webservice.utils.CurrencyValidator;
import com.vali.webservice.utils.PropertiesReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * Service class responsible with providing implementation for desired API(s).
 */
@Service
public class ParseCSV {

    @Autowired
    public PropertiesReader propertiesReader;

    @Autowired
    public CSVReader csvReader;

    @Autowired
    public CurrencyValidator currencyValidator;

    /**
     * Method that returns the average value for a provided currency.
     * @param currency String
     * @return double value
     */
    public double getAverage(String currency) {
        // perform validation if currency is supported
        currencyValidator.validateCurrency(currency);

        // obtain the configuration path for the csv file.
        final String currenciesFullPath = propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);
        currencyValidator.validateFullPath(currenciesFullPath);

        // parse the csv file (using apache library) and return the records.
        List<CSVRecord> records = csvReader.getCSVRecords(currenciesFullPath);

        BigDecimal result = new BigDecimal(0);
        MathContext mc = new MathContext(MathContext.DECIMAL32.getPrecision(), RoundingMode.UP);

        int count = 0;
        for (CSVRecord record : records) {
            String currencyInFile = record.get(CurrencyHeader.currency);
            if (currencyInFile.equals(currency)) {
                count++;
                double value = Double.parseDouble(record.get(CurrencyHeader.value));
                result = result.add(new BigDecimal(value), mc);
            }
        }

        // if there are no records, return result should be 0
        if (count > 0) {
            result = result.divide(new BigDecimal(count), mc);
        }

        // return the average
        return result.doubleValue();
    }

    /**
     * Method that returns the sorted currencies present in the CVS file.
     * @return List of strings
     */
    public List<String> getAvailableCurrencies() {
        final String currenciesName = propertiesReader.getConfigValue(CSVUtils.CURRENCIES_FULL_PATH);

        Set<String> uniqueSet = new HashSet<>();

        List<CSVRecord> records = csvReader.getCSVRecords(currenciesName);
        for (CSVRecord record : records) {
            String currencyInFile = record.get(CurrencyHeader.currency);
            uniqueSet.add(currencyInFile);
        }

        // SORT CURRENCIES ASC
        List<String> result = new ArrayList<>(uniqueSet);
        Collections.sort(result);
        return result;
    }

}
