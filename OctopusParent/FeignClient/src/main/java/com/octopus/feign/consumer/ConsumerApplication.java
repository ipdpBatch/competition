package com.octopus.feign.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerApplication {
    private final static Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main(String[] args) {
        logger.info("消费者客户端开始启动");
        SpringApplication.run(ConsumerApplication.class, args);
        logger.info("消费者客户端启动成功!!");
    }

}
