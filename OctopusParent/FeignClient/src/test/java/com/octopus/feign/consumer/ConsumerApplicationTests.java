package com.octopus.feign.consumer;


import com.octopus.feign.consumer.ConsumerApplication;
import com.octopus.feign.consumer.rabbitMq.HelloSender;
import com.octopus.feign.consumer.rabbitMq.Receiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class ConsumerApplicationTests {


    @Autowired
    private HelloSender helloSender;

    @Autowired
    private Receiver receiver;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendOrder() {
        helloSender.send();
    }

    @Test
    public void receiverOrder() {

        receiver.receivertest1( );
    }
}
