package dev.java10x.user.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMq {

    private static final String EXCHANGE_NAME = "exchange-email";
    private static final String ROUT_NAME = "email-queue";

    @Bean
    public Jackson2JsonMessageConverter jsonConverter (){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
