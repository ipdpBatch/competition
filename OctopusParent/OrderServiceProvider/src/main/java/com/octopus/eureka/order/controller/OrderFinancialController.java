package com.octopus.eureka.order.controller;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.common.dao.mapper.OrderFinancialMapper;
import com.octopus.eureka.order.service.OrderFinancialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019/9/26 11:59 PM
 * @Version 1.0
 */
@RestController
public class OrderFinancialController {

    private final static Logger logger = LoggerFactory.getLogger(OrderFinancialController.class);
    @Autowired
    OrderFinancialMapper orderFinancialMapper;
    @Autowired
    private OrderFinancialService orderFinancialService;


    @RequestMapping("/order/{orderSeq}")
    public OrderFinancialDto getOrder(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return orderFinancialMapper.selectById(orderSeq);
    }

    @RequestMapping("/order/all")
    public List<OrderFinancialDto> getOrderList(@RequestParam(value = "customerId",required = false) String customerId) {
        logger.info("请求参数customerId："+ customerId);
        return orderFinancialMapper.selectAll(customerId);
    }

    @RequestMapping("/order/add")
    public int addOrder(@RequestBody  OrderFinancialDto orderFinancialDto) {
        return orderFinancialMapper.insert(orderFinancialDto);
    }

    @RequestMapping("/order/update")
    public int updateOrder(@RequestBody OrderFinancialDto orderFinancialDto) {
        return orderFinancialMapper.update(orderFinancialDto);
    }

    @RequestMapping("/order/delete/{orderSeq}")
    public int deleteOrder(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return orderFinancialMapper.delete(orderSeq);
    }

    @RequestMapping("/order/createOrder")
    public BuyResponseBo createOrder(@RequestBody BuyBo buyBo) {
        logger.info("OrderFinancialController层请求创建订单服务：请求参数："+ buyBo.toString());
        return orderFinancialService.createOrder(buyBo);
    }

    @RequestMapping("/order/finishOrder")
    public BuyResponseBo finishOrder(@RequestBody BuyBo buyBo) {
        logger.info("OrderFinancialController层请求更新订单服务：请求参数："+ buyBo.toString());
        return orderFinancialService.finishOrder(buyBo);
    }

}
