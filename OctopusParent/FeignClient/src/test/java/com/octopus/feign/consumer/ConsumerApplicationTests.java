package com.octopus.feign.consumer;

import org.junit.Test;
import com.octopus.common.dao.ControlOrderDto;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.octopus.feign.consumer.rabbitMq.HelloSender;
import com.octopus.feign.consumer.rabbitMq.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class ConsumerApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(ConsumerApplicationTests.class);
    @Autowired
    private OctopusHandController octopusHandController;


    @Autowired
    private HelloSender helloSender;

    @Autowired
    private Receiver receiver;

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendOrder() {
        helloSender.send();
    }

    @Test
    public void testOrder() {
//        List<ControlOrderDto> controlOrderList = octopusHandController.getControlOrderList();
//        logger.info("查询结果："+controlOrderList.toString());
        ControlOrderDto controlOrder = octopusHandController.getControlOrder("13");
        logger.info("查询结果：" + controlOrder.toString());
//        ControlOrderDto controlOrderDto = new ControlOrderDto(null,new Date(),new Date(),"ESTB","INIT");
////        controlOrderDto.setOrderSeq("12312312313");
////        controlOrderDto.setOrderStep("VOLF");
////        controlOrderDto.setStepStatus("PCSC");
////        controlOrderDto.setRequestTime(new Date());
////        controlOrderDto.setRequestTime(new Date());
//        logger.info("新建controlOrderDto记录："+controlOrderDto.toString());
//        int result = octopusHandController.addControlOrder(controlOrderDto);
//        logger.info("插入后的controlOrderDto："+result);
//        logger.info("插入条数：" + result);
//
//        ControlOrderDto controlOrderDto2 = new ControlOrderDto("3",new Date(),new Date(),"VOLF","PCFL");
//        int result2 = octopusHandController.updateControlOrder(controlOrderDto2);
//        logger.info("更新条数：" + result2);
////        int result3 = octopusHandController.deleteControlOrder("1");
//        logger.info("删除条数：" + result3);
    }

    @Test
    public void receiverOrder() {

        receiver.receivertest1( );

    }
}
