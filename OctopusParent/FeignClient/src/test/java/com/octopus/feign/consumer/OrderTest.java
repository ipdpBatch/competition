package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.common.utils.DateUtil;
import com.octopus.feign.consumer.ConsumerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019/10/12 8:46 PM
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class OrderTest {

    private final static Logger logger = LoggerFactory.getLogger(OrderTest.class);
    @Autowired
    private OrderDispatcher orderDispatcher;

    @Test
    public void testControlOrder() {
//        List<ControlOrderDto> controlOrderList = orderDispatcher.getControlOrderList();
//        logger.info("查询结果："+controlOrderList.toString());
//        ControlOrderDto controlOrder = orderDispatcher.getControlOrder(BigInteger.valueOf(1));
//        logger.info("查询结果：" + controlOrder.toString());
       ControlOrderDto controlOrderDto = new ControlOrderDto();
        controlOrderDto.setOrderSeq(BigInteger.valueOf(3));
        controlOrderDto.setOrderStep("PCSC");
        controlOrderDto.setStepStatus("PCSC");
       controlOrderDto.setRequestTime(DateUtil.formatTime(new Date()));
       controlOrderDto.setUpdateTime(DateUtil.formatTime(new Date()));
//       logger.info("新建controlOrderDto记录："+controlOrderDto.toString());
//       int result = orderDispatcher.addControlOrder(controlOrderDto);
//        logger.info("插入后的controlOrderDto："+result);
//        logger.info("插入条数：" + result);
////
//        ControlOrderDto controlOrderDto2 = new ControlOrderDto("3",new Date(),new Date(),"VOLF","PCFL");
        int result2 = orderDispatcher.updateControlOrder(controlOrderDto);
        logger.info("更新条数：" + result2);
//        int result3 = orderDispatcher.deleteControlOrder(BigInteger.valueOf(2));
//        logger.info("删除条数：" + result3);
    }

    @Test
    public void testorder() {
        //查询所有
//        String custmerId = null;
//        List<OrderFinancialDto> orderList1 = orderDispatcher.getOrderList(custmerId);
//        logger.info("查询所有订单："+orderList1.toString());
//        //查询dongjl
//        custmerId="dongjl";
//        List<OrderFinancialDto> orderList2 = orderDispatcher.getOrderList(custmerId);
//        logger.info("查询每个客户所有订单："+orderList2.toString());
//        //根据id查询
//        OrderFinancialDto order = orderDispatcher.getOrder(BigInteger.valueOf(2019100906));
//        logger.info("根据id查询订单：" + order.toString());
//        新增
        OrderFinancialDto orderDto = new OrderFinancialDto();
        orderDto.setOrderSeq(BigInteger.valueOf(20191013));
        orderDto.setCreateDate("20190930");
        orderDto.setCreateTime("222222");
        orderDto.setTransactionVol(BigDecimal.valueOf(1000));
        orderDto.setTransactionCode("022");
        logger.info("新建controlOrderDto记录："+orderDto.toString());
        int result = orderDispatcher.addOrder(orderDto);
        logger.info("插入后的controlOrderDto："+result);
        logger.info("插入条数：" + result);
////        修改
////        orderDto.setOrderSeq(123);
////        orderDto.setProductId("000539");
////        orderDto.setCustomerId("2222222");
////        logger.info("修改DTO：" + orderDto.toString());
////        int result2 = orderDispatcher.updateOrder(orderDto);
////        logger.info("更新条数：" + result2);
//        int result3 = orderDispatcher.deleteOrder(BigInteger.valueOf(19942222));
//        logger.info("删除条数：" + result3);
    }

    @Test
    public void testCreateOrder(){
        BuyBo buyBo = new BuyBo();
        buyBo.setOrderSeq(BigInteger.valueOf(201912123));
        buyBo.setBusinessCode("024");
        buyBo.setCustomerId("11111111");
        buyBo.setTransactionAmount(new BigDecimal(1000));
        buyBo.setProductId("033315");
        BuyResponseBo order = orderDispatcher.createOrder(buyBo);
//        BuyResponseBo order = orderDispatcher.finishOrder(buyBo);
        logger.info("创建的订单为："+order);
    }



}
