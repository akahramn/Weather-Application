package com.kahraman.cyangateweather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * The WeatherBindingData class implements Processor Interface.
 * This class include helper method deserialize Json Object.
 * @author Abdullah Kahraman
 */
@Component
public class WeatherBindingData implements Processor {

    /**
     * Process method deserialize Json object. Creates new WeatherResultModel
     * and set values to this model. After that set WeatherResultModel to exchange value.
     * @param exchange
     * @throws Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonString = exchange.getMessage().getBody(String.class);
        JsonNode weatherNode = new ObjectMapper().readTree(jsonString);
        WeatherResultModel weatherResultModel = new WeatherResultModel();
        weatherResultModel.setCountry(CountryCode.valueOf(weatherNode.get("sys").get("country").textValue()).getValue());
        weatherResultModel.setName(weatherNode.get("name").textValue());
        weatherResultModel.setTemp(weatherNode.get("main").get("temp").intValue());
        weatherResultModel.setDateTime(DateTimeConverter.calculateDateTime(weatherNode.get("dt").longValue()));
        exchange.getIn().setBody(weatherResultModel);
    }
}
