package com.octopus.blitz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//开启缓存
@EnableCaching
@Slf4j
public class BlitzApplication {
	public static void main(String[] args) {
		log.info("额度控销微服务开始注册...");
		SpringApplication.run(BlitzApplication.class, args);
		log.info("额度控销微服务注册成功!");
	}
}
