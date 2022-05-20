package com.kahraman.cyangateweather;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * A CsvProcessor implements Processor Interface.
 * The CsvProcessor class includes helper methods for manipulate data.
 *
 * @author Abdullah Kahraman
 */
@Component
public class CsvProcessor implements Processor {

    /**
     * Process method take Exchange type data from api and convert
     * this data to String type. After that split this String line by line.
     * After that take second line and set this value to exchange value.
     * @param exchange
     * @throws Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        String csvBody = exchange.getMessage().getBody(String.class);

        String[] strings = csvBody.split("\n", 2);
        exchange.getIn().setBody(strings[1]);
    }
}
