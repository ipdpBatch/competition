package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlUserDto;
import com.octopus.common.dao.domain.PositionBalanceDto;
import com.octopus.feign.consumer.provider.UserClient;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.octopus.common.utils.DateUtil.formatTime;

@RestController
public class UserDispatcher {
    private final static Logger logger = LoggerFactory.getLogger(UserDispatcher.class);

    @Autowired
    UserClient userClient;

    public BuyResponseBo precheck(BuyBo buybo){
        return userClient.preCheck(buybo);
    }

    //控制表
    public List<ControlUserDto> getControlUserList() {
        return userClient.getControlUserList();
    }

    public ControlUserDto getControlUser(BigInteger orderSeq) {
        return userClient.getControlUser(orderSeq);
    }

    public int deleteControlUser(BigInteger orderSeq) {
        return userClient.deleteControlUser(orderSeq);
    }

    public int updateControlUser(ControlUserDto controlUserDto) {
        return userClient.updateControlUser(controlUserDto);
    }

    public int addControlUser(ControlUserDto controlUserDto) {
        return userClient.addControlUser(controlUserDto);
    }

    //持仓表
    public List<PositionBalanceDto> getPositionList() {
        return userClient.getPositionList();
    }

    public PositionBalanceDto getPosition(String productId, String customerId) {
        return userClient.getPosition(productId, customerId);
    }

    public int deletePosition(String productId, String customerId) {
        return userClient.deletePosition(productId, customerId);
    }

    public int updatePosition(PositionBalanceDto positionBalanceDto) {
        return userClient.updatePosition(positionBalanceDto);
    }

    public int addPosition(PositionBalanceDto positionBalanceDto) {
        return userClient.addPosition(positionBalanceDto);
    }


    /*加仓服务*/
    public PositionBalanceDto incPosition(BuyBo buyBo) {
        //插入控制表
        ControlUserDto controlUserDto = new ControlUserDto();
        controlUserDto.setOrderSeq(buyBo.getOrderSeq());
        controlUserDto.setOrderStep(buyBo.getOrderStep());
        controlUserDto.setRequestTime(formatTime(new Date()));
        controlUserDto.setUpdateTime(formatTime(new Date()));
        controlUserDto.setStepStatus("INIT");
        int i = userClient.addControlUser(controlUserDto);
        if (i != 1) {
            logger.info("插入用户控制表失败");
            controlUserDto.setUpdateTime(formatTime(new Date()));
            controlUserDto.setStepStatus("PCFL");
            int j=userClient.updateControlUser(controlUserDto);
            return null;
        } else {
            PositionBalanceDto position = userClient.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
            if (position == null){
                PositionBalanceDto positionBalanceDto = new PositionBalanceDto();
                positionBalanceDto.setCustomerId(buyBo.getCustomerId());
                positionBalanceDto.setProductId(buyBo.getProductId());
                positionBalanceDto.setTotalVolume(buyBo.getTransactionAmount());
                positionBalanceDto.setPositionStatus("norm");
                PositionBalanceDto newPosition = userClient.getAddPosition(positionBalanceDto);
                logger.info("用户持仓信息为："+newPosition.toString());
                controlUserDto.setUpdateTime(formatTime(new Date()));
                controlUserDto.setStepStatus("PCSC");
                int j=userClient.updateControlUser(controlUserDto);
                return newPosition;
            }else {
                BigDecimal amout = position.getTotalVolume().add(buyBo.getTransactionAmount());
                position.setTotalVolume(amout);
                int update = userClient.updatePosition(position);
                PositionBalanceDto newPosition = userClient.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
                logger.info("用户持仓信息为："+newPosition.toString());
                controlUserDto.setUpdateTime(formatTime(new Date()));
                controlUserDto.setStepStatus("PCSC");
                int j=userClient.updateControlUser(controlUserDto);
                return newPosition;
            }
        }
    }

    /*减仓服务*/
    public PositionBalanceDto descPosition(BuyBo buyBo) {
        //插入控制表
        ControlUserDto controlUserDto = new ControlUserDto();
        controlUserDto.setOrderSeq(buyBo.getOrderSeq());
        controlUserDto.setOrderStep(buyBo.getOrderStep());
        controlUserDto.setRequestTime(formatTime(new Date()));
        controlUserDto.setUpdateTime(formatTime(new Date()));
        controlUserDto.setStepStatus("INIT");
        int i = userClient.addControlUser(controlUserDto);
        if (i != 1) {
            logger.info("插入用户控制表失败");
            controlUserDto.setUpdateTime(formatTime(new Date()));
            controlUserDto.setStepStatus("PCFL");
            int j=userClient.updateControlUser(controlUserDto);
            return null;
        } else {
            PositionBalanceDto position = userClient.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
            BigDecimal amout = position.getTotalVolume().subtract(buyBo.getTransactionAmount());
            position.setTotalVolume(amout);
            if (amout.compareTo(BigDecimal.ZERO) == 0){
                position.setPositionStatus("N");
            }
            int update = userClient.updatePosition(position);
            PositionBalanceDto newPosition = userClient.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
            logger.info("用户持仓信息为："+position.toString());
            controlUserDto.setUpdateTime(formatTime(new Date()));
            controlUserDto.setStepStatus("PCSC");
            int j=userClient.updateControlUser(controlUserDto);
            return newPosition;
        }
    }
}
