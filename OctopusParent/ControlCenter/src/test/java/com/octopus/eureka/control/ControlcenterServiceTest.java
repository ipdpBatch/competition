package com.octopus.eureka.control;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.octopus.common.dao.mapper.ControlCentureMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlcenterServiceTest.class)
@MapperScan("com.octopus.common.dao")
public class ControlcenterServiceTest {
	@Autowired
	ControlCentureMapper controlCentureMapper;
	
	@Test
     public void selectById(){
		controlCentureMapper.selectById("1");
	}
	
}
