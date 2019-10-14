package com.octopus.blitz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//开启缓存
@EnableCaching
public class BlitzApplication {
	private final static Logger logger = LoggerFactory.getLogger(BlitzApplication.class);
	public static void main(String[] args) {
		logger.info("额度控销微服务开始注册");
		SpringApplication.run(BlitzApplication.class, args);
		logger.info("额度控销微服务注册成功!!");
	}
}
