package com.octopus.eureka.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.octopus.common.bo.BuyBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 20:50 2019/9/27
 */
@Component
public class IMqSenderImpl implements IMqSender {
    @Autowired
    MqSender mqSender;
    @Override
    public int sendBuyMessage(BuyBo buyBo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String buyJsonStr = objectMapper.writeValueAsString(buyBo);
        return sendByte(buyJsonStr);
    }

    private int sendByte(String buyJsonStr) {
        mqSender.send(buyJsonStr);
        return 0;
    }
}
