package com.kahraman.cyangateweather;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterTest {
    @Test
    void calculateDateTimeFromDt() {
        //Given
        Long dt = 1653051430L;
        //When
        String expected = DateTimeConverter.calculateDateTime(dt);
        //Then
        assertThat(expected).isEqualTo("2022-05-20 03:57:10");
    }
}