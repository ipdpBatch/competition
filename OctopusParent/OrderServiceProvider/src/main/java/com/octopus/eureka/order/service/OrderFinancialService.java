package com.octopus.eureka.order.service;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.common.dao.mapper.ControlOrderMapper;
import com.octopus.common.dao.mapper.OrderFinancialMapper;
import com.octopus.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private  ControlOrderMapper controlOrderMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public BuyResponseBo createOrder(BuyBo buyBo){

        //创建订单步骤为ESTB
        ControlOrderDto controlOrderDto = new ControlOrderDto();
        controlOrderDto.setOrderSeq(buyBo.getOrderSeq());
        controlOrderDto.setRequestTime(DateUtil.getNowToday());
        controlOrderDto.setOrderStep("ESTB");
        controlOrderDto.setStepStatus("INIT");
        int insertResult1 = controlOrderMapper.insert(controlOrderDto);

        if (insertResult1 != 1) {
            logger.error("订单控制表初始化插入失败：" + controlOrderDto.toString());
        }
        logger.info("订单控制表初始化插入成功：" + controlOrderDto.toString());
        //创建订单
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
        order.setCreateDate(DateUtil.getNowToday());
        order.setCreateTime(DateUtil.getNowTime());
        //订单初始状态
        order.setOrderStatus("INIT");
        //订单
        order.setCapitalStatus("INIT");
        //插入订单
        int insertResult2 = orderFinancialMapper.insert(order);
        //封装返回Bo
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        buyResponseBo.setBusinessCode(buyBo.getBusinessCode());
        buyResponseBo.setCustomerId(buyBo.getCustomerId());
        buyResponseBo.setDestiny(buyBo.getDestiny());
        buyResponseBo.setOrderSeq(buyBo.getOrderSeq());
        buyResponseBo.setProductId(buyBo.getProductId());
        buyResponseBo.setTransactionAmount(buyBo.getTransactionAmount());
        buyResponseBo.setOrderStep(buyBo.getOrderStep());

        //更新订单控制表状态
        if(insertResult2 == 1){
            logger.error("订单表插入成功：" + order.toString());
            controlOrderDto.setUpdateTime(DateUtil.getNowToday());
            controlOrderDto.setStepStatus("PCSC");
            int updateResult1 = controlOrderMapper.update(controlOrderDto);
            if (updateResult1 != 1) {
                logger.error("订单控制表更新失败：" + controlOrderDto.toString());
            }
            logger.info("订单控制表初始化更新成功：" + controlOrderDto.toString());
            buyResponseBo.setOrderReturnCode(true);
        }else {
            logger.error("订单表插入失败：" + order.toString());
            controlOrderDto.setUpdateTime(DateUtil.getNowToday());
            controlOrderDto.setStepStatus("PCFL");
            int updateResult2 = controlOrderMapper.update(controlOrderDto);
            if (updateResult2 != 1) {
                logger.error("订单控制表更新失败：" + controlOrderDto.toString());
            }
            logger.info("订单控制表初始化更新成功：" + controlOrderDto.toString());
            buyResponseBo.setOrderReturnCode(false);
        }
        return buyResponseBo;

    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public BuyResponseBo finishOrder(BuyBo buyBo){
        //订单完成步骤为FNSH
        ControlOrderDto controlOrderDto = new ControlOrderDto();
        controlOrderDto.setOrderSeq(buyBo.getOrderSeq());
        controlOrderDto.setRequestTime(DateUtil.getNowToday());
        controlOrderDto.setOrderStep("FNSH");
        controlOrderDto.setStepStatus("INIT");
        int insertResult1 = controlOrderMapper.insert(controlOrderDto);
        if (insertResult1 != 1) {
            logger.error("订单控制表初始化插入失败：" + controlOrderDto.toString());
        }
        logger.info("订单控制表初始化插入成功：" + controlOrderDto.toString());
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
        //订单状态
        order.setOrderStatus("AOSZ");
        //订单资金状态
        order.setCapitalStatus("DSZZ");
        //插入订单
        int updateResult1 = orderFinancialMapper.update(order);

        //封装返回Bo
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        buyResponseBo.setBusinessCode(buyBo.getBusinessCode());
        buyResponseBo.setCustomerId(buyBo.getCustomerId());
        buyResponseBo.setDestiny(buyBo.getDestiny());
        buyResponseBo.setOrderSeq(buyBo.getOrderSeq());
        buyResponseBo.setProductId(buyBo.getProductId());
        buyResponseBo.setTransactionAmount(buyBo.getTransactionAmount());
        buyResponseBo.setOrderStep(buyBo.getOrderStep());

        if(updateResult1 == 1){
            logger.error("订单完成时更新订单状态成功：" + order.toString());
            //更新订单控制表状态
            controlOrderDto.setUpdateTime(DateUtil.getNowToday());
            controlOrderDto.setStepStatus("PCSC");
            int updateResult2 = controlOrderMapper.update(controlOrderDto);
            if (updateResult2 != 1) {
                logger.error("订单控制表更新失败：" + controlOrderDto.toString());
            }
            logger.info("订单控制表初始化更新成功：" + controlOrderDto.toString());
            buyResponseBo.setOrderReturnCode(true);
        }else {
            logger.error("订单完成时更新订单状态失败：" + order.toString());
            //更新订单控制表状态
            controlOrderDto.setUpdateTime(DateUtil.getNowToday());
            controlOrderDto.setStepStatus("PCFL");
            int updateResult3 = controlOrderMapper.update(controlOrderDto);
            if (updateResult3 != 1) {
                logger.error("订单控制表更新失败：" + controlOrderDto.toString());
            }
            logger.info("订单控制表初始化更新成功：" + controlOrderDto.toString());
            buyResponseBo.setOrderReturnCode(false);
        }
        return buyResponseBo;
    }
}
