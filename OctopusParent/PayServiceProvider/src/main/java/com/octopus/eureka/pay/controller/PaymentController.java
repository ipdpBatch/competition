package com.octopus.eureka.pay.controller;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.PaymentInfoDto;
import com.octopus.common.dao.mapper.PaymentInfoMapper;
import com.octopus.eureka.pay.service.PaymentInfoService;
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
public class PaymentController {
    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private PaymentInfoService paymentInfoService;


    @Value("${server.port}")
    String port;
    @RequestMapping("/pay")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/pay/{orderSeq}/{payType}")
    public PaymentInfoDto getPay(@PathVariable("orderSeq") BigInteger orderSeq, @PathVariable("payType")String payType) {
        PaymentInfoDto PaymentInfoDto = paymentInfoMapper.selectById(orderSeq,payType);
        if (PaymentInfoDto != null) {
            return PaymentInfoDto;
        } else {
            return null;
        }
    }

    @RequestMapping("/pay/delete/{orderSeq}/{payType}")
    public int deletePay(@PathVariable("orderSeq") BigInteger orderSeq, @PathVariable("payType")String payType) {
        logger.info("请求参数orderSeq："+ orderSeq + payType);
        return paymentInfoMapper.delete(orderSeq, payType);
    }

    @RequestMapping("/pay/update")
    public int updatePay(@RequestBody PaymentInfoDto paymentInfoDto) {
        return paymentInfoMapper.update(paymentInfoDto);
    }

    @GetMapping("/pay/all")
    public List<PaymentInfoDto> getPayList() {
        return paymentInfoMapper.selectAll();
    }

    @RequestMapping("/pay/add")
    public int addPay(@RequestBody  PaymentInfoDto paymentInfoDto) {
        return paymentInfoMapper.insert(paymentInfoDto);
    }

    @RequestMapping("/pay/process")
    public BuyResponseBo processPay(@RequestBody BuyBo buyBo) {
        logger.info("PaymentInfoController层请求支付服务：请求参数："+ buyBo.toString());
        return paymentInfoService.processPay(buyBo);
    }
}
