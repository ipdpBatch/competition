package com.octopus.eureka.user;

import com.octopus.common.bo.BuyBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=UserServiceApplication.class)
public class preCheckTest {

    @Resource
    PreCheckService preCheckService;

    @Test
    public void test(){
        BuyBo test = new BuyBo();
        test.setBusinessCode("110");
        test.setCustomerId("1111111");
        BigInteger orderSeq = BigInteger.valueOf(12);
        test.setOrderSeq(orderSeq);
        test.setProductId("01000");
        test.setTransactionAmount(new BigDecimal(100.00));
        test.setDestiny("order");
        int result = preCheckService.doProcess(test);
        System.out.println("----" + result);
    }

}
