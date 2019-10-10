package com.octopus.feign.consumer.rabbitMq;

import com.alibaba.fastjson.JSONObject;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlUserDto;
import com.octopus.common.utils.DateUtil;
import com.octopus.feign.consumer.BuyConsumer;
import com.octopus.feign.consumer.UserDispatcher;
import com.octopus.common.enums.MicroService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


@Component
public class MqListener {

    @Autowired
    UserDispatcher userDispatcher;
    @Resource
    MicroService microService;
    @Resource
    BuyConsumer buyConsumer;


    @RabbitHandler
    @RabbitListener(queues = "Control")
    public void process(String content) {
          System.out.println(content + "这是接收到的队列");
          BuyBo buybo = JSONObject.parseObject(content , BuyBo.class);
          buyConsumer.buy(buybo);
    }
}
