package com.octopus.feign.consumer.provider;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.ProductBaseInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author tiansheng
 * @Date 2019/9/27 7:31 PM
 * @Version 1.0
 */
@FeignClient("eureka-provider-blitz")
public interface QuotaClient {

    @RequestMapping(value = "/quota/process", method = RequestMethod.POST)
    ProductBaseInfoDto checkQuota(@RequestBody BuyBo buyBo);

}
