package com.octopus.feign.consumer.rabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.octopus.feign.consumer.rabbitMq.RabbitMqConfig.QUEUE_NAME;

@Component
public class HelloSender {


    // spring boot 为我们提供的包装类
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello1111 " + new Date();
        System.out.println("Sender : " + context);
// 调用 发送消息的方法
        this.rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME, context);
    }

}
