package com.octopus.eureka;

import com.octopus.common.dao.domain.CustomerCifInfoDto;
import com.octopus.common.dao.mapper.CustomerCifInfoMapper;
import com.octopus.eureka.user.UserServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=UserServiceApplication.class)
public class UserServiceApplicationTests {

    @Resource
    private CustomerCifInfoMapper customerCifInfoMapper;

    @Test
    public void test(){
        CustomerCifInfoDto user = new CustomerCifInfoDto("abcs81728", "田生", "11010119217839","1","C5","Y");
        int i = customerCifInfoMapper.insert(user);
        System.out.println(i+"rows have been inserted!!");

    }

}
