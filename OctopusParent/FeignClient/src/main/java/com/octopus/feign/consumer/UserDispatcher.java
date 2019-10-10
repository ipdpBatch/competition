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


    public BuyResponseBo addPosition(BuyBo buyBo){
        return userClient.addPosition(buyBo);
    }

    public BuyResponseBo descPosition(BuyBo buyBo){
        return userClient.descPosition(buyBo);
    }
}
