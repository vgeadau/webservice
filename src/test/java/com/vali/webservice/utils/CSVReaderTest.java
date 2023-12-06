package com.vali.webservice.utils;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CSVReader}.
 */
@ExtendWith(MockitoExtension.class)
public class CSVReaderTest {
    private static final int SIZE = 4;

    CSVReader csvReader = new CSVReader();

    @Test
    public void getCSVRecords_should_return_results() {
        // given
        String fullPath = "c:/Users/Valentin/IdeaProjects/currencies.csv";

        // when
        List<CSVRecord> results = csvReader.getCSVRecords(fullPath);

        // then
        assertEquals(SIZE, results.size());
    }

    @Test
    public void getCSVRecords_should_return_emptyList() {
        // given
        String fullPath = "c:/Users/Valentin/IdeaProjects/other.csv";

        // when
        List<CSVRecord> results = csvReader.getCSVRecords(fullPath);

        // then
        assertTrue(results.isEmpty());
    }
}
