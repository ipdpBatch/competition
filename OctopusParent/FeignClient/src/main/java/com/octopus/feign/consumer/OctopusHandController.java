package com.octopus.feign.consumer;

import com.octopus.common.dao.*;
import com.octopus.feign.consumer.provider.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class OctopusHandController {
//    @Autowired
//    private HomeClient homeClient;
    @Autowired
    private OrderClient orderClient;

//    public String findAllUsers(){
//        return homeClient.findAllUser();
//    }
//    public String findById(String id){
//        return homeClient.findById(id);
//    }

//    @Autowired
//    private HomeClient homeClient;
//
//    public String findAllUsers(){
//        return homeClient.findAllUser();
//    }
//    public String findById(String id){
//        return homeClient.findById(id);
//    }


    //订单控制表
    public List<ControlOrderDto> getControlOrderList(){
        return  orderClient.getControlOrderList();
    }

    public ControlOrderDto getControlOrder(String orderSeq) {
        return orderClient.getControlOrder(orderSeq);
    }

    public int deleteControlOrder(String orderSeq){
        return  orderClient.deleteControlOrder(orderSeq);
    }
    public int updateControlOrder(ControlOrderDto controlOrderDto){
        return orderClient.updateControlOrder(controlOrderDto);
    }
    public int addControlOrder(ControlOrderDto controlOrderDto){
        return orderClient.addControlOrder(controlOrderDto);
    }

    //订单表
    public List<OrderFinancialDto> getOrderList(){
        return orderClient.getOrderList();
    }
    public OrderFinancialDto getOrder(String orderSeq){
        return orderClient.getOrder(orderSeq);
    }
    public int deleteOrder(String orderSeq){
        return orderClient.deleteOrder(orderSeq);
    }
    public int updateOrder(OrderFinancialDto orderFinancialDto){
        return orderClient.updateOrder(orderFinancialDto);
    }
    public int addOrder(OrderFinancialDto orderFinancialDto){
        return orderClient.addOrder(orderFinancialDto);
    }

}