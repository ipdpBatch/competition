package com.octopus.feign.consumer.rabbitMq;

import com.octopus.feign.consumer.ConsumerApplication;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("myQueue"))
public class Receiver {
    //1\. @RabbitListener(queues = "myQueue") // 不能自动创建队列
    //2\. 自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3\. 自动创建, Exchange和Queue绑定
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        logger.info("MqReceiver: {}", message);
    }

    /**
     * 数码供应商服务 接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message) {
        logger.info("computer MqReceiver: {}", message);
    }

    /**
     * 水果供应商服务 接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message) {
        logger.info("fruit MqReceiver: {}", message);
    }
}
