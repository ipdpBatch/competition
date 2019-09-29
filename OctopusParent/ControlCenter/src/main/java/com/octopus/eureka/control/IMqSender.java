package com.octopus.eureka.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.common.bo.BuyBo;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 20:34 2019/9/27
 */
interface IMqSender {
     int sendBuyMessage(BuyBo buyBo) throws JsonProcessingException;
}
