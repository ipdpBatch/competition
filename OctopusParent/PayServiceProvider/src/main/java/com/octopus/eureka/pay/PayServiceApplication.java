package com.octopus.eureka.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PayServiceApplication {
    private final static Logger logger = LoggerFactory.getLogger(PayServiceApplication.class);
    public static void main(String[] args) {
        logger.info("支付微服务开始注册");
        SpringApplication.run(PayServiceApplication.class, args);
        logger.info("支付微服务注册成功!!");
    }

}
