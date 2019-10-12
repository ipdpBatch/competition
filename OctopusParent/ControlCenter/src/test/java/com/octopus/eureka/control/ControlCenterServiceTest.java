package com.octopus.eureka.control;

import com.octopus.common.dao.domain.ControlCenterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.octopus.common.dao.mapper.ControlCenterMapper;

import java.math.BigInteger;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlCenterApplication.class)
public class ControlCenterServiceTest {
	@Autowired
	ControlCenterMapper controlCenterMapper;
	
	@Test
     public void selectById(){
		ControlCenterDto controlCenterDto = new ControlCenterDto();
		controlCenterDto = controlCenterMapper.selectById(BigInteger.valueOf(1));
		System.out.println(controlCenterDto.toString());
	}
	
}
