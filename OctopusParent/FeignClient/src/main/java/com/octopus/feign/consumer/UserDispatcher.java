package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.feign.consumer.provider.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDispatcher {
    @Autowired
    UserClient userClient;
    public int precheck(BuyBo buybo){
        return userClient.preCheck(buybo);
    }
}
