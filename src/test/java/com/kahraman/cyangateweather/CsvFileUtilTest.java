package com.kahraman.cyangateweather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CsvFileUtilTest {
    private CsvFileUtil csvFileUtil;

    @BeforeEach
    void setUp() {
        csvFileUtil = new CsvFileUtil();
    }

    @Test
    void checkCsvFileExist() {
        //When
        Boolean expected = csvFileUtil.checkCsvFile();
        //Then
        assertThat(expected).isTrue();
    }


}