package com.octopus.eureka.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
public class OctopusEarController {
    private final static Logger logger = LoggerFactory.getLogger(OctopusEarController.class);
    @Autowired
    private HomeClient homeClient;

    @GetMapping(value = "/customerId/{id}/productId/{pid}/transactionAmount/{txnamt}")
    public String buyRequest(@PathVariable("id") String id, @PathVariable("pid") String pid, @PathVariable("txnamt") BigDecimal txnamt) {
        logger.info("接收交易请求如下:【客户号】"+id+"【产品代码】"+pid+"【交易金额】"+txnamt);
        return null;
    }
}