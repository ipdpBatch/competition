package com.octopus.eureka.user;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=UserServiceApplication.class)
public class preCheckTest {

    @Resource
    PreCheckService preCheckService;
    @Resource
    UserController userController;
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
        BuyResponseBo result = userController.preCheck(test);
        System.out.println("----" + result.getErrorDetail());
    }

}
