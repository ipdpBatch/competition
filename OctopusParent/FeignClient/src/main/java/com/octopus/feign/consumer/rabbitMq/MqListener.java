package com.octopus.feign.consumer.rabbitMq;

import com.alibaba.fastjson.JSONObject;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.feign.consumer.UserDispatcher;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MqListener {

    @Autowired
    UserDispatcher userDispatcher;
    @RabbitHandler
    @RabbitListener(queues = "Control")
    public void process(String content) {
          System.out.println(content + "这是接收到的队列");
          BuyBo buybo = JSONObject.parseObject(content , BuyBo.class);
          String destiny = buybo.getDestiny();
          switch (destiny){
              case "order" :
                  BuyResponseBo checkFlag = userDispatcher.precheck(buybo);
                  if(true){
                      System.out.println("预检查成功");
                  }else{
                      System.out.println("预检查失败");
                  }
                  System.out.println("order");
                  break;
              case "product":
                  System.out.println("product");
                  break;
              case "pay":
                  System.out.println("pay");
                  break;
              case "user":
                  System.out.println("user");
                  break;
              case "control":
                  System.out.println("control");
                  break;
              default:
                  System.out.println("对象输入错误");
                  break;
          }

    }
}
