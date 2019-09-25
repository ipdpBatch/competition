package com.octopus.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);
    public static void main(String[] args) {
        logger.info("用户微服务开始注册");
        SpringApplication.run(UserServiceApplication.class, args);
        logger.info("用户微服务注册成功!!");
    }
}
