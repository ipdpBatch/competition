package com.octopus.eureka.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ControlCentureApplication {
    private final static Logger logger = LoggerFactory.getLogger(ControlCentureApplication.class);
    public static void main(String[] args) {
        logger.info("控制中心开始启动");
        SpringApplication.run(ControlCentureApplication.class, args);
        logger.info("控制中心启动成功!!");
    }
}
