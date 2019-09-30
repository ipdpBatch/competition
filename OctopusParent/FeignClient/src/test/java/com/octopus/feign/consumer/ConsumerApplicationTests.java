package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.OrderFinancialDto;
import org.junit.Test;
import com.octopus.common.dao.domain.ControlOrderDto;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.octopus.feign.consumer.rabbitMq.HelloSender;
import com.octopus.feign.consumer.rabbitMq.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class ConsumerApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(ConsumerApplicationTests.class);
    @Autowired
    private OrderDispatcher orderDispatcher;


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
    public void testControlOrder() {
       List<ControlOrderDto> controlOrderList = orderDispatcher.getControlOrderList();
        logger.info("查询结果："+controlOrderList.toString());
        ControlOrderDto controlOrder = orderDispatcher.getControlOrder("1111");
        logger.info("查询结果：" + controlOrder.toString());
//       ControlOrderDto controlOrderDto = new ControlOrderDto();
//        controlOrderDto.setOrderSeq("1");
////        controlOrderDto.setOrderStep("VOLF");
////        controlOrderDto.setStepStatus("PCSC");
//       controlOrderDto.setRequestTime("20190733");
//       controlOrderDto.setUpdateTime("20190866");
//       logger.info("新建controlOrderDto记录："+controlOrderDto.toString());
//       int result = orderDispatcher.addControlOrder(controlOrderDto);
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

    @Test
    public void testorder() {
        //查询所有
        List<OrderFinancialDto> orderList = orderDispatcher.getOrderList();
        logger.info("查询结果："+orderList.toString());
        //根据id查询
        OrderFinancialDto order = orderDispatcher.getOrder(12344455);
        logger.info("查询结果：" + orderList.toString());
        //新增
        OrderFinancialDto orderDto = new OrderFinancialDto();
//        orderDto.setOrderSeq(123);
        orderDto.setCreateDate("20190930");
        orderDto.setCreateTime("222222");
        logger.info("新建controlOrderDto记录："+orderDto.toString());
        int result = orderDispatcher.addOrder(orderDto);
        logger.info("插入后的controlOrderDto："+result);
        logger.info("插入条数：" + result);
        //修改
        orderDto.setOrderSeq(123);
        orderDto.setProductId("000539");
        orderDto.setCustomerId("2222222");
        logger.info("修改DTO：" + orderDto.toString());
        int result2 = orderDispatcher.updateOrder(orderDto);
        logger.info("更新条数：" + result2);
//        int result3 = orderDispatcher.deleteOrder(1);
//        logger.info("删除条数：" + result3);
    }

    @Test
    public void testCreateOrder(){
        BuyBo buyBo = new BuyBo();
        buyBo.setBusinessCode("022");
        buyBo.setCustomerId("dongjl");
        buyBo.setTransactionAmount(new BigDecimal(10000000));
        buyBo.setProductId("033315");
        OrderFinancialDto order = orderDispatcher.createOrder(buyBo);
        logger.info("创建的订单为："+order);
    }
}
