package com.octopus.feign.consumer.provider;

import java.math.BigInteger;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;

/**
 * @Author dongjiale
 * @Date 2019/9/27 7:31 PM
 * @Version 1.0
 */
@FeignClient("eureka-provider-order")
public interface OrderClient {

    //订单控制表

    @RequestMapping(value = "/controlOrder/all")
    List<ControlOrderDto> getControlOrderList();

    @RequestMapping(value ="/controlOrder/{orderSeq}")
    ControlOrderDto getControlOrder(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/controlOrder/delete/{orderSeq}")
    int deleteControlOrder(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/controlOrder/update")
    int updateControlOrder(@RequestBody ControlOrderDto controlOrderDto);

    @RequestMapping(value ="/controlOrder/add")
    int addControlOrder(@RequestBody ControlOrderDto controlOrderDto);

    //订单表
    @RequestMapping(value ="/order/all")
    List<OrderFinancialDto> getOrderList(@RequestParam(value = "customerId",required = false) String customerId);

    @RequestMapping(value ="/order/{orderSeq}")
    OrderFinancialDto getOrder(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/order/delete/{orderSeq}")
    int deleteOrder(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/order/add")
    int addOrder(@RequestBody OrderFinancialDto orderFinancialDto);

    @RequestMapping("/order/update")
    int updateOrder(@RequestBody OrderFinancialDto orderFinancialDto);

    @RequestMapping(value ="/order/createOrder")
    BuyResponseBo createOrder(@RequestBody BuyBo buyBo);

    @RequestMapping(value = "/order/finishOrder")
    BuyResponseBo finishOrder(@RequestBody BuyBo buyBo);
}
