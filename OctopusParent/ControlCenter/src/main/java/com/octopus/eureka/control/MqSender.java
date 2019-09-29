package com.octopus.eureka.control;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MqSender {
    // spring boot 为我们提供的包装类
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String buyJsonStr) {
        System.out.println(buyJsonStr);
// 调用 发送消息的方法
        this.rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME, buyJsonStr);
    }

    public void send1() {
        String context = "hello1111 " + new Date();
        System.out.println("Sender : " + context);
// 调用 发送消息的方法
        this.rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME, context);
    }
}
