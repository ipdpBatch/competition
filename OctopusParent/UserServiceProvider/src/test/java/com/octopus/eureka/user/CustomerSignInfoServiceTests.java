package com.octopus.eureka.user;

import com.octopus.common.bo.BuyBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=UserServiceApplication.class)
public class CustomerSignInfoServiceTests {

    @Resource
    CustomerSignService customerSignService;
    @Test
    public void test(){
        String customerId = "09275921";
        if(customerSignService.doProcess(customerId)){
            System.out.println("签约成功");
        }else{
            System.out.println("签约失败");
        }
    }
}