package com.octopus.eureka.control;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 19:06 2019/9/26
 */

@FeignClient("eureka-provider-user")
public interface  HomeClient {
    @GetMapping("/user/all")
    String findAllUser();

    @GetMapping("/user/{id}")
    String findById(@PathVariable("id") String id) ;
}