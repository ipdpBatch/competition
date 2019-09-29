package com.octopus.feign.consumer.provider;

import com.octopus.common.bo.BuyBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.ws.RequestWrapper;

@FeignClient("eureka-provider-user")
public interface UserClient {

    @RequestMapping(value = "/user/precheck/{buybo}", method = RequestMethod.GET)
    int preCheck(@PathVariable("buybo")BuyBo buybo);
}
