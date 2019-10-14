package com.octopus.eureka.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.common.bo.BuyBo;
import com.octopus.eureka.ControlCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlCenterApplication.class)
public class ControlCenterApplicationTests {
    @Autowired
    OctopusEarController octopusEarController;
    @Resource
    IMqSenderImpl iMqSenderImpl;

    @Test
    public void sendMessage() {
        try {
            BuyBo test = new BuyBo();
            test.setBusinessCode("110");
            test.setCustomerId("1111111");
            test.setOrderSeq(BigInteger.valueOf(100));
            test.setProductId("01000");
            test.setTransactionAmount(new BigDecimal(100.00));
            test.setDestiny("order");
            iMqSenderImpl.sendBuyMessage(test);
        } catch (JsonProcessingException e){
            System.out.println(e);
        }
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void findByUsers(){
       //System.out.println(consumerController.findAllUsers());
       //System.out.println(octopusEarController.findById("a219391"));
    }
}
