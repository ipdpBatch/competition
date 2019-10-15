package com.octopus.feign.consumer.rabbitMq;

import com.alibaba.fastjson.JSONObject;
import com.octopus.common.bo.BuyBo;
import com.octopus.feign.consumer.BuyConsumer;
import com.octopus.feign.consumer.UserDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MqListener {
    private final static Logger logger = LoggerFactory.getLogger(MqListener.class);

    @Autowired
    UserDispatcher userDispatcher;
    @Autowired
    BuyConsumer buyConsumer;


    @RabbitHandler
    @RabbitListener(queues = "Control")
    public void process(String content) {
          logger.error(content + "这是接收到的队列");
          BuyBo buybo = JSONObject.parseObject(content , BuyBo.class);
        try {
            buyConsumer.buy(buybo);
        } catch (Exception e) {
            logger.error("MQ侦听层调用买入交易报错！！");
            e.printStackTrace();
        }
    }
}
