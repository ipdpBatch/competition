package com.octopus.eureka;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        UserDao user = new UserDao("abcs81728", "田生", "11010119217839","1","C5","Y");
        int i =userMapper.insert(user);
        System.out.println(i+"rows have been inserted!!");

    }

}
