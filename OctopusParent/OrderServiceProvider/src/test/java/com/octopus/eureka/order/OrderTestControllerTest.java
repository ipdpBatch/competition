package com.octopus.eureka.order;

import com.mysql.cj.log.LogFactory;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.eureka.OrderServiceApplicationTests;
import com.octopus.eureka.order.controller.OrderFinancialController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:19 2019/9/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceApplication.class)
@MapperScan("com.octopus.common.dao")
public class OrderTestControllerTest {

    private final static Logger logger = LoggerFactory.getLogger(OrderTestControllerTest.class);
    @Autowired
    OrderFinancialController orderFinancialController;

    @Test
    public void home() {
    }

    @Test
    public void getControlOrder() {
    }

    @Test
    public void getControlOrderList() {
    }

    @Test
    public void testOrder() {
//        OrderFinancialDto orderDto = new OrderFinancialDto();
//        orderDto.setOrderSeq(BigInteger.valueOf(20191013));
//        orderDto.setCreateDate("20190930");
//        orderDto.setCreateTime("222222");
//        orderDto.setTransactionVol(BigDecimal.valueOf(1000));
////        orderDto.setTransactionCode("022");
//
//        logger.info("新建controlOrderDto记录："+orderDto.toString());
//        int result = orderFinancialController.createOrder(orderDto);
//        logger.info("插入后的controlOrderDto："+result);
//        logger.info("插入条数：" + result);
    }
}