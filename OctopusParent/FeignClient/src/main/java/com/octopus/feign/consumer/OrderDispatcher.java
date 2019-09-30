package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.feign.consumer.provider.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderDispatcher {
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

    public OrderFinancialDto getOrder(float orderSeq) {
        return orderClient.getOrder(orderSeq);
    }

    public int deleteOrder(float orderSeq) {
        return orderClient.deleteOrder(orderSeq);
    }

    public int updateOrder(OrderFinancialDto orderFinancialDto) {
        return orderClient.updateOrder(orderFinancialDto);
    }

    public int addOrder(OrderFinancialDto orderFinancialDto) {
        return orderClient.addOrder(orderFinancialDto);
    }

    /*订单建单服务*/
    public OrderFinancialDto createOrder(BuyBo buyBo) {
        OrderFinancialDto orderFinancialDto = new OrderFinancialDto();
        orderFinancialDto.setCustomerId(buyBo.getCustomerId());
        orderFinancialDto.setTransactionAmount(buyBo.getTransactionAmount());
        orderFinancialDto.setTransactionCode(buyBo.getBusinessCode());
        orderFinancialDto.setProductId(buyBo.getProductId());
        OrderFinancialDto order = orderClient.getAddOrder(orderFinancialDto);

        return order;
    }
}