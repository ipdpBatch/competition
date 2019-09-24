package com.ipdp.bond.serviceprovider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述: 指定这个接口所要调用的 提供者服务名称 "eureka-provider"
 * 通过@FeignClient（"服务名"），来指定调用哪个服务。
 * 比如在代码中调用了eureka-provider服务的 / 接口，/ 就是调用：服务提供者项目的 home() 方法，代码如下
 *
 * @author yanpenglei
 * @create 2017-12-06 15:13
 **/
@FeignClient("eureka-provider-order")
public interface  HomeClient {

    @GetMapping("/")
    String consumer();
}
