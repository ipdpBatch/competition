package com.octopus.eureka.order.service;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.common.dao.mapper.OrderFinancialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author dongjiale
 * @Date 2019/10/9 7:06 PM
 * @Version 1.0
 */
@Component
public class OrderFinancialService {

    private final static Logger logger = LoggerFactory.getLogger(OrderFinancialService.class);

    @Autowired
    private OrderFinancialMapper orderFinancialMapper;

    public BuyResponseBo createOrder(BuyBo buyBo){
        //封装返回Bo
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        buyResponseBo.setBusinessCode(buyBo.getBusinessCode());
        buyResponseBo.setCustomerId(buyBo.getCustomerId());
        buyResponseBo.setDestiny(buyBo.getDestiny());
        buyResponseBo.setOrderSeq(buyBo.getOrderSeq());
        buyResponseBo.setProductId(buyBo.getProductId());
        buyResponseBo.setTransactionAmount(buyBo.getTransactionAmount());
        buyResponseBo.setOrderStep(buyBo.getOrderStep());
        //
        OrderFinancialDto order = new OrderFinancialDto();
        //订单编号
        order.setOrderSeq(buyBo.getOrderSeq());
        //客户号
        order.setCustomerId(buyBo.getCustomerId());
        //产品码
        order.setProductId(buyBo.getProductId());
        //交易码
        order.setTransactionCode(buyBo.getBusinessCode());
        switch (order.getTransactionCode()){
            case "020":
                //认购-赋值交易金额
                order.setTransactionAmount(buyBo.getTransactionAmount());
            case "022":
                //申购-赋值交易金额
                order.setTransactionAmount(buyBo.getTransactionAmount());
            case "024":
                //赎回-赋值交易份额
                order.setTransactionVol(buyBo.getTransactionAmount());
            default:
                //赋值交易金额
                order.setTransactionAmount(buyBo.getTransactionAmount());
        }
        DateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
        Date date = new Date();
        String dateFormate = format.format(date);
        String[] datelist = dateFormate.split(" ");
        //建单日期
        order.setCreateDate(datelist[0]);
        //建单时间
        order.setCreateTime(datelist[1]);
        //订单初始状态
        order.setOrderStatus("INIT");
        //订单
        order.setCapitalStatus("INIT");
        //插入订单
        try{
            int insertResult = orderFinancialMapper.insert(order);
            if(insertResult == 1){
                buyResponseBo.setOrderReturnCode(true);
            }else {
                buyResponseBo.setOrderReturnCode(false);
            }
            return buyResponseBo;
        }catch (Exception e){
            buyResponseBo.setOrderReturnCode(false);
            buyResponseBo.setErrorDetail(e.getMessage());
            return buyResponseBo;
        }
    }
}
