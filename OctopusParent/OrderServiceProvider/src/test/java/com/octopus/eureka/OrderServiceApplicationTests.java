package com.octopus.eureka;

import com.octopus.common.dao.mapper.ControlOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceApplicationTests.class)
@MapperScan("com.octopus.common.dao")
public class OrderServiceApplicationTests {
    @Autowired
    ControlOrderMapper controlOrderMapper;

    @Test
    public void contextLoads() {
        controlOrderMapper.selectById("12");
    }

}
