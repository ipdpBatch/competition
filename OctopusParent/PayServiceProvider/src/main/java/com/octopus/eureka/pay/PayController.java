package com.octopus.eureka.pay;

import com.octopus.eureka.pay.dao.PaymentInfoDto;
import com.octopus.eureka.pay.dao.PaymentInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;


    @Value("${server.port}")
    String port;
    @RequestMapping("/pay")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/pay/{orderSeq}/{payType}")
    public PaymentInfoDto findById(@PathVariable("orderSeq")float  orderSeq,@PathVariable("payType")String payType) {
        PaymentInfoDto PaymentInfoDto = paymentInfoMapper.selectById(orderSeq,payType);
        if (PaymentInfoDto != null) {
            return PaymentInfoDto;
        } else {
            return null;
        }
    }

    @GetMapping("/pay/all")
    public List<PaymentInfoDto> findAll() {
        return paymentInfoMapper.selectAll();
    }
}
