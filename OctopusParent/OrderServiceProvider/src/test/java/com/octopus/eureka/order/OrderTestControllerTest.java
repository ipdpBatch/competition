package com.octopus.eureka.order;

import com.octopus.eureka.OrderServiceApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void home() {
    }

    @Test
    public void getControlOrder() {
    }

    @Test
    public void getControlOrderList() {
    }
}