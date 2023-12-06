package com.vali.webservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WebserviceApplicationTest {

    @Test
    public void main_should_succeed() {
        // given
        boolean errorFound = false;

        // when main method is called
        try {
            WebserviceApplication.main(new String[]{});
        } catch (Exception exception) {
            errorFound = true;
        }

        // then no error should happen
        assertFalse(errorFound);
    }
}
