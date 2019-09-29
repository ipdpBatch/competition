package com.octopus.eureka.control;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControlCenterApplicationTests {
    @Autowired
    OctopusEarController octopusEarController;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findByUsers(){
       //System.out.println(consumerController.findAllUsers());
       //System.out.println(octopusEarController.findById("a219391"));
    }
}
