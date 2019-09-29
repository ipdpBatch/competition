package com.octopus.eureka.control;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.octopus.common.dao")
public class ControlCenterApplication {
    private final static Logger logger = LoggerFactory.getLogger(ControlCenterApplication.class);
    public static void main(String[] args) {
        logger.info("控制中心开始启动");
        SpringApplication.run(ControlCenterApplication.class, args);
        logger.info("控制中心启动成功!!");
    }
}
