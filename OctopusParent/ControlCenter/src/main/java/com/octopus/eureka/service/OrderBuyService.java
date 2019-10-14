package com.octopus.eureka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.ControlCenterDto;
import com.octopus.common.dao.mapper.ControlCenterMapper;
import com.octopus.common.utils.DateUtil;
import com.octopus.eureka.control.IMqSenderImpl;
import com.octopus.eureka.control.MqSender;
import com.octopus.eureka.control.OrderBuyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Date;

/**
 * @Author dongjiale
 * @Date 2019/10/12 7:29 PM
 * @Version 1.0
 */
@Component
public class OrderBuyService {

    private final static Logger logger = LoggerFactory.getLogger(OrderBuyService.class);

    @Autowired
    private ControlCenterMapper controlCenterMapper;

    @Autowired
    private IMqSenderImpl iMqSenderImpl;

    public BuyBo orderBuy (BuyBo buyBo) {
        logger.info("OrderBuyService中orderBuy方法接收的参数buyBo为：" + buyBo.toString());
        //创建订单前置步骤：创建控制表中
        ControlCenterDto controlCenterDto = new ControlCenterDto();

        //请求日期
        String todayDate = DateUtil.getTodayDate();
        controlCenterDto.setRequestTime(todayDate);
        //订单初始步骤
        controlCenterDto.setOrderStep("INIT");
        //订单初始状态
        controlCenterDto.setOrderStatus("INIT");
        //业务结束标志
        controlCenterDto.setFlag("N");

        int insertResult = controlCenterMapper.insert(controlCenterDto);

        if (insertResult > 0 && controlCenterDto.getOrderSeq() != null) {
            //插入成功,将控制中心生成订单编号分配给buyBo
            buyBo.setOrderSeq(controlCenterDto.getOrderSeq());
        }
        //填充订单步骤
        buyBo.setOrderStep(controlCenterDto.getOrderStep());
        //发送MQ
        try {
            logger.info("开始向MQ传递订单数据..."+ buyBo);
            iMqSenderImpl.sendBuyMessage(buyBo);
        } catch (JsonProcessingException e) {
            logger.info("向MQ传递订单数据失败...");
            e.printStackTrace();
        }
        return buyBo;
    }



}
