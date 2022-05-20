package com.kahraman.cyangateweather;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration
class WeatherBindingDataTest extends CamelTestSupport {
    @Autowired
    private CamelContext camelContext;

    @Autowired
    private WeatherBindingData weatherBindingData;

    @EndpointInject("mock:result")
    private MockEndpoint resultEndpoint;

    @Produce("direct:start")
    private ProducerTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .process(new WeatherBindingData())
                        .to("mock:result");
            }
        });

        camelContext.start();
    }

    @DirtiesContext
    @Test
    void process() throws JSONException {
        resultEndpoint.expectedMessageCount(1);
        JSONObject myProcessorInput = new JSONObject(
                "{\"main\":{\"temp\":17.34},\"dt\":1653051430,\"sys\":{\"country\":\"TR\"},\"name\":\"Manisa Province\"}"
        );
        template.sendBody(myProcessorInput);
        WeatherResultModel body = resultEndpoint.getExchanges().get(0).getIn().getBody(WeatherResultModel.class);
        WeatherResultModel expectedWeather = new WeatherResultModel();
        expectedWeather.setCountry("TURKEY");
        expectedWeather.setDateTime("2022-05-20 03:57:10");
        expectedWeather.setTemp(17);
        expectedWeather.setName("Manisa Province");
        assertEquals(body, expectedWeather);
    }
}