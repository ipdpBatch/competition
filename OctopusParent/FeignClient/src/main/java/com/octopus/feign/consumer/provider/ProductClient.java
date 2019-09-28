package com.octopus.feign.consumer.provider;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author dongjiale
 * @Date 2019/9/27 7:35 PM
 * @Version 1.0
 */
@FeignClient("eureka-provider-product")
public interface ProductClient {

}
