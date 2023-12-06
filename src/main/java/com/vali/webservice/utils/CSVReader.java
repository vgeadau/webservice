package com.vali.webservice.utils;

import com.vali.webservice.dto.CurrencyHeader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for parsing the csv file using apache library.
 */
@Component
public class CSVReader {

    /**
     * parses a csv file at the given location and file name.
     * @param fullPath String
     * @return a list of CSVRecord(s)
     */
    public List<CSVRecord> getCSVRecords(String fullPath) {
        List<CSVRecord> results = new ArrayList<>();

        try (final Reader in = new FileReader(fullPath)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(CurrencyHeader.class)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);
            for (CSVRecord record : records) {
                results.add(record);
            }
        } catch (IOException ioe) {
            // IO Error
        }

        return results;
    }
}
