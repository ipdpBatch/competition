package com.octopus.feign.consumer.provider;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlPayDto;
import com.octopus.common.dao.domain.PayAccountDto;
import com.octopus.common.dao.domain.PaymentInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019/9/27 7:33 PM
 * @Version 1.0
 */
@FeignClient("eureka-provider-pay")
public interface PayClient {
    //订单控制表

    @RequestMapping(value = "/controlPay/all", method = RequestMethod.GET)
    List<ControlPayDto> getControlPayList();

    @RequestMapping(value ="/controlPay/{orderSeq}", method = RequestMethod.GET)
    ControlPayDto getControlPay(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/controlPay/delete/{orderSeq}",method = RequestMethod.DELETE)
    int deleteControlPay(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/controlPay/update/",method = RequestMethod.POST)
    int updateControlPay(@RequestBody ControlPayDto controlPayDto);

    @RequestMapping(value ="/controlPay/add/",method = RequestMethod.POST)
    int addControlPay(@RequestBody ControlPayDto controlPayDto);
    @RequestMapping(value = "/pay/all", method = RequestMethod.GET)
    List<PaymentInfoDto> getPayList();

    @RequestMapping(value ="/pay/{orderSeq}/{payType}", method = RequestMethod.GET)
    PaymentInfoDto getPay(@PathVariable("orderSeq") BigInteger orderSeq, @PathVariable("payType") String payType);

    @RequestMapping(value ="/pay/delete/{orderSeq}/{payType}",method = RequestMethod.DELETE)
    int deletePay(@PathVariable("orderSeq") BigInteger orderSeq, @PathVariable("payType") String payType);

    @RequestMapping(value ="/pay/update/",method = RequestMethod.POST)
    int updatePay(@RequestBody PaymentInfoDto paymentInfoDto);

    @RequestMapping(value ="/pay/add/",method = RequestMethod.POST)
    int addPay(@RequestBody PaymentInfoDto paymentInfoDto);


    @RequestMapping(value = "/payaccount/all", method = RequestMethod.GET)
    List<PayAccountDto> getPayaccountList();

    @RequestMapping(value ="/payaccount/{customerId}", method = RequestMethod.GET)
    PayAccountDto getPayaccount(@PathVariable("customerId") String customerId);

    @RequestMapping(value ="/payaccount/delete/{customerId}",method = RequestMethod.DELETE)
    int deletePayaccount(@PathVariable("customerId") String customerId);


    @RequestMapping(value ="/payaccount/update/",method = RequestMethod.POST)
    int updatePayaccount(@RequestBody PayAccountDto payAccountDto);

    @RequestMapping(value ="/payaccount/add/",method = RequestMethod.POST)
    int addPayaccount(@RequestBody PayAccountDto payAccountDto);

    @RequestMapping(value ="/pay/process/",method = RequestMethod.POST)
    BuyResponseBo processPay(@RequestBody BuyBo buyBo);

}
