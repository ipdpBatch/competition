package com.octopus.eureka.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/octopus")
public class OctopusEarController {
    private final static Logger logger = LoggerFactory.getLogger(OctopusEarController.class);

    @Autowired
    private IMqSender mqSender;


    /*@GetMapping(value = "/customerId/{customerId}/productId/{productId}/transactionAmount/{transactionAmount}")
    public String buyRequest(@PathVariable("customerId") String id, @PathVariable("productId") String pid, @PathVariable("transactionAmount") BigDecimal txnamt) {
        logger.info("接收交易请求如下:【客户号】"+id+"【产品代码】"+pid+"【交易金额】"+txnamt);
        return null;
    }*/

    @GetMapping("/get")
    public Date get() {
        return new Date();
    }

    @GetMapping("/post")
    public Map<String, String> test(@RequestParam Map<String, String> fieldValueList) {
        return fieldValueList;
    }

    @GetMapping("/posta")
    public String post(@RequestParam String username, @RequestParam String password) {
        return "Username:" + username + "'\n'password:" + password;
    }

    @GetMapping("/buy")
    public int buy(@RequestParam String businessCode, @RequestParam String customerId, @RequestParam String productId, @RequestParam BigDecimal transactionAmount) {
            //return "businessCode:" + businessCode + "\ncustomerId:" + customerId +"\nproductId:"+productId +"\ntransactionAmount:"+ transactionAmount ;
      BuyBo buyBo = new BuyBo(businessCode,customerId,productId,transactionAmount);
      //1.将买入请求写入控制中心的t_control_order中，同时得到订单编号
      // 2.发送MQ001到feign client，由consumer完成调用建单服务
        buyBo.setDestiny("ORDER");
        buyBo.setOrderStep("ESTB");
        int res= 0;
        try {
            res = mqSender.sendBuyMessage(buyBo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }





}