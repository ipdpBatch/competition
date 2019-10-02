package com.octopus.feign.consumer.provider;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.PositionBalanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("eureka-provider-user")
public interface UserClient {

    @RequestMapping(value = "/user/precheck/{buybo}", method = RequestMethod.GET)
    int preCheck(@PathVariable("buybo")BuyBo buybo);

    //持仓控制表

    //持仓表
    @RequestMapping(value ="/positionBalance/all", method = RequestMethod.GET)
    List<PositionBalanceDto> getPositionList();

    @RequestMapping(value ="/positionBalance/{productId}/{customerId}", method = RequestMethod.GET)
    PositionBalanceDto getPosition(@PathVariable("productId") String productId, @PathVariable("customerId") String customerId);

    @RequestMapping(value ="/positionBalance/delete/{productId}/{customerId}", method = RequestMethod.DELETE)
    int deletePosition(@PathVariable("productId") String productId, @PathVariable("customerId") String customerId);

    @RequestMapping(value = "/positionBalance/update",method = RequestMethod.POST)
    int updatePosition(@RequestBody PositionBalanceDto positionBalanceDto);

    @RequestMapping(value ="/positionBalance/add",method = RequestMethod.POST)
    int addPosition(@RequestBody PositionBalanceDto positionBalanceDto);

    @RequestMapping(value ="/positionBalance/getAdd")
    PositionBalanceDto getAddPosition(@RequestBody PositionBalanceDto positionBalanceDto);
}
