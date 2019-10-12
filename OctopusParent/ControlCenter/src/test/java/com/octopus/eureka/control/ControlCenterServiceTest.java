package com.octopus.eureka.control;

import com.octopus.common.dao.domain.ControlCenterDto;
import com.octopus.common.utils.DateUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.octopus.common.dao.mapper.ControlCenterMapper;

import java.math.BigInteger;
import java.util.Date;


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

	@Test
     public void insert(){
		ControlCenterDto controlCenterDto = new ControlCenterDto();
		controlCenterDto.setOrderStep("INIT");
		controlCenterDto.setFlag("N");
		controlCenterDto.setOrderStatus("INIT");
		controlCenterDto.setRequestTime(DateUtil.formatTime(new Date()));
		controlCenterDto.setUpdateTime(DateUtil.formatTime(new Date()));
		System.out.println("插入前dto:"+controlCenterDto.toString());
		int res = controlCenterMapper.insert(controlCenterDto);
		System.out.println("插入结果为:"+res);
		System.out.println("插入后dto变为:"+controlCenterDto.toString());
	}
	
}
