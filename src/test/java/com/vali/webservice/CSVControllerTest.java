package com.vali.webservice;

import com.vali.webservice.services.ParseCSV;
import com.vali.webservice.utils.CSVUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CSVController}.
 */
@ExtendWith(MockitoExtension.class)
public class CSVControllerTest {
    private static final String CURRENCY = "Some Currency";
    private static final double AVERAGE = 0d;

    @Mock
    public ParseCSV parseCSV;

    @InjectMocks
    public CSVController csvController = new CSVController();

    @Test
    public void getAverage_should_succeed() {
        // given
        when(parseCSV.getAverage(CURRENCY)).thenReturn(AVERAGE);

        // when
        double result = csvController.getAverage(CURRENCY);

        // then
        verify(parseCSV).getAverage(CURRENCY);
        verifyNoMoreInteractions(parseCSV);

        assertEquals(AVERAGE, result, CSVUtils.DELTA);
    }

    @Test
    public void getAvailableCurrencies_should_succeed() {
        // given
        when(parseCSV.getAvailableCurrencies()).thenReturn(new ArrayList<>());

        // when
        List<String> result = csvController.getAvailableCurrencies();

        // then
        verify(parseCSV).getAvailableCurrencies();
        verifyNoMoreInteractions(parseCSV);

        assertTrue(result.isEmpty());
    }

}
