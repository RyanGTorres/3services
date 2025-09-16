package dev.java10x.email.configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMq {
    private final static String queueName = "email-queue";

    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonConverter (){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}

