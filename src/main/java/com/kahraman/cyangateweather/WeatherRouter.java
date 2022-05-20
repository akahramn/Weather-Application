package com.kahraman.cyangateweather;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.stereotype.Component;

/**
 * The WeatherRouter extends from RouterBuilder.
 * This creates weather api. Take weather results from OpenWeather Api.
 *
 * @author Abdullah Kahraman
 */
@Component
public class WeatherRouter extends RouteBuilder {

    /**
     * Configure method take weather results in Json Object format
     * and convert this results .csv file format.
     * Save this file to the inside project directory.
     * Keep this operations at the startup of the application and
     * also every subsequent hour while application is running.
     *
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from("scheduler://foo?repeatCount=1")
                .routeId("csvFileNotExist")
                .to("weather:deneme?location=Manisa,Turkey&appid={{weatherAppId}}&units=METRIC")
                .setHeader("isFileExist", method(CsvFileUtil.class))
                .choice()
                    .when(header("isFileExist").isEqualTo(false))
                        .process(new WeatherBindingData())
                        .marshal().bindy(BindyType.Csv, WeatherResultModel.class)
                        .to("file:src/main/resources/repo?fileName=newSampleUser.csv")
                .end();

        //cron:tab?schedule=1/10+*+*+*+*+?
        from("cron:tab?schedule=0+*+*+*+?")
                .routeId("csvFileExist")
                .to("weather:deneme?location=Manisa,Turkey&appid={{weatherAppId}}&units=METRIC")
                .setHeader("isFileExist", method(CsvFileUtil.class))
                .choice()
                    .when(header("isFileExist").isEqualTo(true))
                        .process(new WeatherBindingData())
                        .marshal().bindy(BindyType.Csv, WeatherResultModel.class)
                        .process(new CsvProcessor())
                        .to("file:src/main/resources/repo?fileName=newSampleUser.csv&fileExist=Append")
                        .log("Successfully created")
                .end();
    }
}

