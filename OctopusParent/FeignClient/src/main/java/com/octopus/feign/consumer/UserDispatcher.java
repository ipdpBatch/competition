package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.PositionBalanceDto;
import com.octopus.feign.consumer.provider.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDispatcher {
    @Autowired
    UserClient userClient;
    public int precheck(BuyBo buybo){
        return userClient.preCheck(buybo);
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

    /*建仓服务*/
    public PositionBalanceDto createPosition(BuyBo buyBo) {
        PositionBalanceDto positionBalanceDto = new PositionBalanceDto();
        positionBalanceDto.setCustomerId(buyBo.getCustomerId());
        positionBalanceDto.setProductId(buyBo.getProductId());
        PositionBalanceDto position = userClient.getAddPosition(positionBalanceDto);
        return position;
    }
}
