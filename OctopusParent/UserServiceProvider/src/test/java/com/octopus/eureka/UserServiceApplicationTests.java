package com.octopus.eureka;
import com.octopus.eureka.user.dao.CustomerDto;
import com.octopus.eureka.user.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        CustomerDto user = new CustomerDto("abcs81728", "田生", "11010119217839","1","C5","Y");
        int i =userMapper.insert(user);
        System.out.println(i+"rows have been inserted!!");

    }

}
