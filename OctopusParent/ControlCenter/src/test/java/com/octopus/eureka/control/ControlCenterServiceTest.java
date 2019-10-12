package com.octopus.eureka.control;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.octopus.common.dao.mapper.ControlCenterMapper;

import java.math.BigInteger;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlCenterServiceTest.class)
@MapperScan("com.octopus.common.dao")
public class ControlCenterServiceTest {
	@Autowired
	ControlCenterMapper controlCenterMapper;
	
	@Test
     public void selectById(){
		controlCenterMapper.selectById(BigInteger.valueOf(1));
	}
	
}
