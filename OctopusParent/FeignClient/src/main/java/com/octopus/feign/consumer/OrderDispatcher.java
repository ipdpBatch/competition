package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.feign.consumer.provider.OrderClient;
import org.hibernate.validator.internal.util.logging.Log_$logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;


@RestController
public class OrderDispatcher {

    private final static Logger logger = LoggerFactory.getLogger(OrderDispatcher.class);
    //    @Autowired
    //    private HomeClient homeClient;
    @Autowired
    private OrderClient orderClient;

    //订单控制表
    public List<ControlOrderDto> getControlOrderList() {
        return orderClient.getControlOrderList();
    }

    public ControlOrderDto getControlOrder(String orderSeq) {
        return orderClient.getControlOrder(orderSeq);
    }

    public int deleteControlOrder(String orderSeq) {
        return orderClient.deleteControlOrder(orderSeq);
    }

    public int updateControlOrder(ControlOrderDto controlOrderDto) {
        return orderClient.updateControlOrder(controlOrderDto);
    }

    public int addControlOrder(ControlOrderDto controlOrderDto) {
        return orderClient.addControlOrder(controlOrderDto);
    }

    //订单表
    public List<OrderFinancialDto> getOrderList() {
        return orderClient.getOrderList();
    }

    public OrderFinancialDto getOrder(BigInteger orderSeq) {
        return orderClient.getOrder(orderSeq);
    }

    public int deleteOrder(BigInteger orderSeq) {
        return orderClient.deleteOrder(orderSeq);
    }
    //public int updateOrder(OrderFinancialDto orderFinancialDto) {
    //    return orderClient.updateOrder(orderFinancialDto);
    //}

    public int addOrder(OrderFinancialDto orderFinancialDto) {
        return orderClient.addOrder(orderFinancialDto);
    }

    /*订单建单服务*/
    public BuyResponseBo createOrder(BuyBo buyBo) {
        logger.info("OrderDispatcher层请求创建订单，请求参数：" + buyBo.toString());
        BuyResponseBo buyResponseBo = orderClient.createOrder(buyBo);
        if (!buyResponseBo.isOrderReturnCode()){
            logger.error("OrderDispatcher层创建订单结果失败" + buyResponseBo.getErrorDetail());
        }
        return buyResponseBo;
    }

    /*订单完成服务*/
    public BuyResponseBo finishOrder(BuyBo buyBo) {
        logger.info("OrderDispatcher层请求更新订单，请求参数：" + buyBo.toString());
        BuyResponseBo buyResponseBo = orderClient.finishOrder(buyBo);
        if (!buyResponseBo.isOrderReturnCode()){
            logger.error("OrderDispatcher层更新订单结果失败" + buyResponseBo.getErrorDetail());
        }
        return buyResponseBo;
    }
}