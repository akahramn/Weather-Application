package com.kahraman.cyangateweather;

import java.io.File;

/**
 * A CsvFileUtil represent helper methods for .csv file.
 * @author Abdullah Kahraman
 */
public class CsvFileUtil {
    private final File csvFile = new File("src/main/resources/repo/newSampleUser.csv");

    /**
     * Check .csv file is exist or not from the directory,
     * @return if .csv file was exist return true, if not return false.
     */
    public Boolean checkCsvFile() {

        if (csvFile.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
