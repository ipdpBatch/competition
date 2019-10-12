package com.octopus.eureka.pay.controller;

import com.octopus.common.dao.domain.PayAccountDto;
import com.octopus.common.dao.domain.PaymentInfoDto;
import com.octopus.common.dao.mapper.PayAccountMapper;
import com.octopus.common.dao.mapper.PaymentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PayAccountController {
    private final static Logger logger = LoggerFactory.getLogger(PayAccountController.class);
    @Autowired
    private PayAccountMapper payAccountMapper;


    @Value("${server.port}")
    String port;
    @RequestMapping("/payaccount")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/payaccount/{customerId}")
    public PayAccountDto getPayAccount(@PathVariable("customerId") String customerId) {
        PayAccountDto payAccountDto = payAccountMapper.selectById(customerId);
        if (payAccountDto != null) {
            return payAccountDto;
        } else {
            return null;
        }
    }

    @GetMapping("/payaccount/all")
    public List<PayAccountDto> getPayAccountList() {
        return payAccountMapper.selectAll();
    }

    @RequestMapping("/payaccount/delete/{customerId}")
    public int deletePayAccount(@PathVariable("customerId") String customerId) {
        logger.info("请求参数orderSeq："+ customerId);
        return payAccountMapper.delete(customerId);
    }

    @RequestMapping("/payaccount/update")
    public int updatePayAccount(@RequestBody PayAccountDto payAccountDto) {
        return payAccountMapper.update(payAccountDto);
    }

    @RequestMapping("/payaccount/add/")
    public int addPay(@RequestBody PayAccountDto payAccountDto) {
        return payAccountMapper.insert(payAccountDto);
    }



}
