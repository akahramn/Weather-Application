package com.kahraman.cyangateweather;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The DateTimeConverter include helper method for calculate current date and timestamp.
 *
 * @author Abdullah Kahraman
 */
public class DateTimeConverter {
    /**
     * CalculateDateTime method take unix date format. Covert
     * this unix time format to YYYY-MM-DD HH-MM-SS format.
     * @param dt unix date format.
     * @return date Stirng type.
     */
    public static String calculateDateTime(Long dt) {
        Long millisecondsDate = (dt * 1000);
        Date currentDate = new Date(millisecondsDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String newDateTime = simpleDateFormat.format(currentDate);
        return newDateTime;
    }
}
