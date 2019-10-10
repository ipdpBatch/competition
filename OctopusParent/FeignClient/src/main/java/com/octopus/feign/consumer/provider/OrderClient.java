package com.octopus.feign.consumer.provider;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019/9/27 7:31 PM
 * @Version 1.0
 */
@FeignClient("eureka-provider-order")
public interface OrderClient {

    //订单控制表

    @RequestMapping(value = "/controlOrder/all", method = RequestMethod.GET)
    List<ControlOrderDto> getControlOrderList();

    @RequestMapping(value ="/controlOrder/{orderSeq}", method = RequestMethod.GET)
    ControlOrderDto getControlOrder(@PathVariable("orderSeq") String orderSeq);

    @RequestMapping(value ="/controlOrder/delete/{orderSeq}",method = RequestMethod.DELETE)
    int deleteControlOrder(@PathVariable("orderSeq") String orderSeq);

    @RequestMapping(value ="/controlOrder/update/",method = RequestMethod.POST)
    int updateControlOrder(@RequestBody ControlOrderDto controlOrderDto);

    @RequestMapping(value ="/controlOrder/add/",method = RequestMethod.POST)
    int addControlOrder(@RequestBody ControlOrderDto controlOrderDto);

    //订单表
    @RequestMapping(value ="/order/all", method = RequestMethod.GET)
    List<OrderFinancialDto> getOrderList();

    @RequestMapping(value ="/order/{orderSeq}", method = RequestMethod.GET)
    OrderFinancialDto getOrder(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/order/delete/{orderSeq}", method = RequestMethod.DELETE)
    int deleteOrder(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/order/add",method = RequestMethod.POST)
    int addOrder(@RequestBody OrderFinancialDto orderFinancialDto);

    @RequestMapping(value ="/order/createOrder")
    BuyResponseBo createOrder(@RequestBody BuyBo buyBo);

    @RequestMapping(value = "/order/finishOrder")
    BuyResponseBo finishOrder(@RequestBody BuyBo buyBo);
}
