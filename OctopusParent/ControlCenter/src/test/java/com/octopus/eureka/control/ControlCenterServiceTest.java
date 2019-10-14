package com.octopus.eureka.control;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.ControlCenterDto;
import com.octopus.common.utils.DateUtil;
import com.octopus.eureka.ControlCenterApplication;
import com.octopus.eureka.service.OrderBuyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.octopus.common.dao.mapper.ControlCenterMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlCenterApplication.class)
public class ControlCenterServiceTest {
	@Autowired
	ControlCenterMapper controlCenterMapper;

	@Autowired
	OrderBuyService orderBuyService;
	
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

	@Test
	public void buy(){
		BuyBo buyBo = new BuyBo();
		buyBo.setProductId("000539");
		buyBo.setCustomerId("a219391");
		buyBo.setBusinessCode("022");
		buyBo.setTransactionAmount(BigDecimal.valueOf(1000));
		System.out.println("插入前dto:"+buyBo.toString());
		BuyBo res = orderBuyService.orderBuy(buyBo);
		System.out.println("插入结果为:"+res);
		System.out.println("插入后dto变为:"+buyBo.toString());
	}
	
}
