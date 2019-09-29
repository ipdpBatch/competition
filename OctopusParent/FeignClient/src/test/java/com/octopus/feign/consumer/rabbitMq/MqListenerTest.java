package com.octopus.feign.consumer.rabbitMq;

import com.octopus.feign.consumer.ConsumerApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class MqListenerTest {
    @Resource
    MqListener mqListener;
    public void mqtest(){
        mqListener.process("1");
    }
}
