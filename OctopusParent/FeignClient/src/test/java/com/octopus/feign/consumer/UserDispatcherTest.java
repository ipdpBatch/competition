package com.octopus.feign.consumer;


import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class UserDispatcherTest {
    @Resource
    UserDispatcher userDispatcher;

    @Test
    public void precheckTest(){
        BuyBo buybo = new BuyBo();
        buybo.setBusinessCode("110");
        buybo.setCustomerId("1111111");
        buybo.setOrderSeq(BigInteger.valueOf(100));
        buybo.setProductId("01000");
        buybo.setTransactionAmount(new BigDecimal(100.00));
        buybo.setDestiny("order");
        BuyResponseBo uuyResponseBo = userDispatcher.precheck(buybo);
        System.out.println(uuyResponseBo.getErrorDetail());
    }

    @Test
    public void signtest(){
        BuyBo buybo = new BuyBo();
        buybo.setBusinessCode("110");
        buybo.setCustomerId("1111111");
        buybo.setOrderSeq(BigInteger.valueOf(100));
        buybo.setProductId("01000");
        buybo.setTransactionAmount(new BigDecimal(100.00));
        buybo.setDestiny("order");
        buybo.setOrderStep("SIGN");
        userDispatcher.customerSignService(buybo);
    }

}
