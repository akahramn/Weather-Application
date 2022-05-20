package com.kahraman.cyangateweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * The WeatherResultModel class represent a .csv file model.
 *
 * @author Abdullah Kahraman
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@CsvRecord(separator = ",", generateHeaderColumns = true, skipFirstLine = true)
public class WeatherResultModel {
    @DataField(pos = 2, columnName = "Country")
    private String country;

    @DataField(pos = 3, columnName = "City")
    private String name;

    @DataField(pos = 4, columnName = "Temperature")
    private Integer temp;

    @DataField(pos = 1, columnName = "DateTime")
    private String dateTime;
}
