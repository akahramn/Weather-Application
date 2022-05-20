package com.kahraman.cyangateweather;

import org.apache.camel.CamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CyanGateWeatherApplication {
    private final CamelContext camelContext;

    public CyanGateWeatherApplication(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CyanGateWeatherApplication.class, args);
    }
}
