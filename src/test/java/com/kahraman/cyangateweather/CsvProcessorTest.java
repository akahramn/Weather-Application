package com.kahraman.cyangateweather;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableAutoConfiguration
class CsvProcessorTest extends CamelTestSupport {
    @Autowired
    private CamelContext camelContext;

    @Autowired
    private CsvProcessor csvProcessor;

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
                        .process(new CsvProcessor())
                        .to("mock:result");
            }
        });
        camelContext.start();
    }

    @DirtiesContext
    @Test
    void process() {
        resultEndpoint.expectedMessageCount(1);
        String myProcessorInput = "DateTime,Country,City,Temperature\n" +
                                  "2022-05-20 06:15:53,TURKEY,Manisa Province,22";
        template.sendBody(myProcessorInput);
        String body = (String)resultEndpoint.getExchanges().get(0).getIn().getBody(String.class);
        assertEquals(body, "2022-05-20 06:15:53,TURKEY,Manisa Province,22");
    }
}