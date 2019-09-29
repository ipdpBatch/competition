package com.octopus.eureka.control;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_NAME = "Control";
    @Bean
    public Queue FeignSend() {
        return new Queue(QUEUE_NAME, true);
    }


}

