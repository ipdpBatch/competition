package com.octopus.blitz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//开启缓存
@EnableCaching
public class BlitzApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlitzApplication.class, args);
	}

}
