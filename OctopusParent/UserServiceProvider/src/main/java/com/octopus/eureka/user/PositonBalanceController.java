package com.octopus.eureka.user;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.PositionBalanceDto;
import com.octopus.common.dao.mapper.PositionBalanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author gsj4877
 * @version 1.0.0
 * @date Created in 2019/10/2 10:36
 */
@RestController
public class PositonBalanceController {
    private final static Logger logger = LoggerFactory.getLogger(PositonBalanceController.class);

    @Resource
    private PositionBalanceMapper positionBalanceMapper;

    @Resource
    private PositionService positionService;

    @Value("${server.port}")
    String port;
    @RequestMapping("/positionBalance")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/positionBalance/all")
    public List<PositionBalanceDto> getPositionList() {
        return positionBalanceMapper.selectAll();
    }

    @RequestMapping("/positionBalance/{productId}/{customerId}")
    public PositionBalanceDto getPosition(@PathVariable("productId") String productId, @PathVariable("customerId") String customerId) {
        logger.info("请求参数productId："+ productId +",请求参数customerId:" + customerId);
        return positionBalanceMapper.selectById(productId, customerId);
    }

    @RequestMapping("/positionBalance/update")
    public int updatePosition(@RequestBody PositionBalanceDto positionBalanceDto) {
        return positionBalanceMapper.update(positionBalanceDto);
    }

    @RequestMapping("/positionBalance/add")
    public int addPosition(@RequestBody  PositionBalanceDto positionBalanceDto) {
        return positionBalanceMapper.insert(positionBalanceDto);
    }

    @RequestMapping("/positionBalance/delete/{productId}/{customerId}")
    public int deletePosition(@PathVariable("productId") String productId, @PathVariable("customerId") String customerId) {
        logger.info("请求参数productId："+ productId +",请求参数customerId:" + customerId);
        return positionBalanceMapper.delete(productId, customerId);
    }

    @RequestMapping("/positionBalance/getAdd")
    public PositionBalanceDto getAddPosition(@RequestBody  PositionBalanceDto positionBalanceDto) {
        int insert = positionBalanceMapper.insert(positionBalanceDto);
        if(insert > 0){
            String productId = positionBalanceDto.getProductId();
            String customerId = positionBalanceDto.getCustomerId();
            logger.info("插入持仓的productId："+ productId +",插入持仓的customerId:" + customerId);
            return positionBalanceMapper.selectById(productId, customerId);
        }
        return null;
    }

    @RequestMapping("/user/addPosition")
    public BuyResponseBo addPosition(@RequestBody BuyBo buybo){
        return positionService.addPosition(buybo);
    }

    @RequestMapping("/user/descPosition")
    public BuyResponseBo descPosition(@RequestBody BuyBo buybo){
        return positionService.descPosition(buybo);
    }

    @RequestMapping("/user/Position/selectDynamic")
    public List<PositionBalanceDto> getPositionDynamic(@RequestBody PositionBalanceDto positionBalanceDto) {
        return positionBalanceMapper.selectDynamic(positionBalanceDto);
    }
}
