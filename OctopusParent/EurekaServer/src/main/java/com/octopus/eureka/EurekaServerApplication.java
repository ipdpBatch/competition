package com.octopus.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 15:09 2019/9/25
 */
@SpringBootApplication
//启动Eureka的注册中心接收服务注册
@EnableEurekaServer
public class EurekaServerApplication {
    private final static Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);

    public static void main(String[] args) {
        logger.info("Eureka注册中心开始启动!!");
        SpringApplication.run(EurekaServerApplication.class, args);
        logger.info("Eureka注册中心启动成功!!");
    }
}