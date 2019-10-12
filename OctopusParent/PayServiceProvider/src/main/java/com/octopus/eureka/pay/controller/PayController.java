package com.octopus.eureka.pay.controller;

import com.octopus.common.dao.domain.ControlPayDto;
import com.octopus.common.dao.mapper.ControlPayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:08 2019/9/23
 */
@RestController
public class PayController {

    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PayController.class);

    @Value("${server.port}")
    String port;

    @Autowired
    ControlPayMapper controlPayMapper;

    @RequestMapping("/home")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/controlPay/{orderSeq}")
    public ControlPayDto getControlPay(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlPayMapper.selectById(orderSeq);
    }

    @GetMapping("/controlPay/all")
    public List<ControlPayDto> getControlPayList() {
        return controlPayMapper.selectAll();
    }

    @RequestMapping("/controlPay/update")
    public int updateControlPay(@RequestBody ControlPayDto controlPayDto) {
        return controlPayMapper.update(controlPayDto);
    }

    @RequestMapping("/controlPay/add")
    public int addControlPay(@RequestBody ControlPayDto controlPayDto) {
        int result = controlPayMapper.insert(controlPayDto);
        logger.info("pay层，插入后主键seq为："+controlPayDto.getOrderSeq());
        return result;
    }

    @RequestMapping("/controlPay/delete/{orderSeq}")
    public int deleteControlPay(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlPayMapper.delete(orderSeq);
    }
}
