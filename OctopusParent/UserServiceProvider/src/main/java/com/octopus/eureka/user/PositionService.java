package com.octopus.eureka.user;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlUserDto;
import com.octopus.common.dao.domain.PositionBalanceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

import static com.octopus.common.utils.DateUtil.formatTime;

/**
 * 文件创建时写入注释内容
 *
 * @author gsj4877
 * @version 1.0.0
 * @date Created in 2019/10/9 19:19
 */
@Component("positionService")
public class PositionService {
    private final static Logger logger = LoggerFactory.getLogger(PositionService.class);

    @Resource
    ControlUserController controlUserController;
    @Resource
    PositonBalanceController positonBalanceController;

    public BuyResponseBo addPosition(BuyBo buyBo){
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        buyResponseBo.setOrderSeq(buyBo.getOrderSeq());
        buyResponseBo.setBusinessCode(buyBo.getBusinessCode());
        buyResponseBo.setCustomerId(buyBo.getCustomerId());
        buyResponseBo.setProductId(buyBo.getProductId());
        buyResponseBo.setTransactionAmount(buyBo.getTransactionAmount());
        buyResponseBo.setDestiny(buyBo.getDestiny());
        buyResponseBo.setOrderStep(buyBo.getOrderStep());
        //插入控制表
        ControlUserDto controlUserDto = new ControlUserDto();
        controlUserDto.setOrderSeq(buyBo.getOrderSeq());
        controlUserDto.setOrderStep(buyBo.getOrderStep());
        controlUserDto.setRequestTime(formatTime(new Date()));
        controlUserDto.setUpdateTime(formatTime(new Date()));
        controlUserDto.setStepStatus("INIT");
        int i = controlUserController.addControlUser(controlUserDto);
        if (i != 1) {
            buyResponseBo.setOrderReturnCode(false);
            buyResponseBo.setErrorDetail("插入用户控制表失败!!");
            controlUserDto.setUpdateTime(formatTime(new Date()));
            controlUserDto.setStepStatus("PCFL");
            int j=controlUserController.updateControlUser(controlUserDto);
            buyResponseBo.setErrorDetail("插入用户控制表失败");
        } else {
            PositionBalanceDto position = positonBalanceController.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
            if (position == null){
                PositionBalanceDto positionBalanceDto = new PositionBalanceDto();
                positionBalanceDto.setCustomerId(buyBo.getCustomerId());
                positionBalanceDto.setProductId(buyBo.getProductId());
                positionBalanceDto.setTotalVolume(buyBo.getTransactionAmount());
                positionBalanceDto.setPositionStatus("norm");
                PositionBalanceDto newPosition = positonBalanceController.getAddPosition(positionBalanceDto);
                logger.info("用户持仓信息为："+newPosition.toString());
                buyResponseBo.setOrderReturnCode(true);
                buyResponseBo.setErrorDetail("成功");
                controlUserDto.setUpdateTime(formatTime(new Date()));
                controlUserDto.setStepStatus("PCSC");
                int j=controlUserController.updateControlUser(controlUserDto);
            }else {
                BigDecimal amout = position.getTotalVolume().add(buyBo.getTransactionAmount());
                position.setTotalVolume(amout);
                int update = positonBalanceController.updatePosition(position);
                PositionBalanceDto newPosition = positonBalanceController.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
                logger.info("用户持仓信息为："+newPosition.toString());
                buyResponseBo.setOrderReturnCode(true);
                buyResponseBo.setErrorDetail("成功");
                controlUserDto.setUpdateTime(formatTime(new Date()));
                controlUserDto.setStepStatus("PCSC");
                int j=controlUserController.updateControlUser(controlUserDto);
            }
        }
        return buyResponseBo;
    }

    public BuyResponseBo descPosition(BuyBo buyBo) {
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        buyResponseBo.setOrderSeq(buyBo.getOrderSeq());
        buyResponseBo.setBusinessCode(buyBo.getBusinessCode());
        buyResponseBo.setCustomerId(buyBo.getCustomerId());
        buyResponseBo.setProductId(buyBo.getProductId());
        buyResponseBo.setTransactionAmount(buyBo.getTransactionAmount());
        buyResponseBo.setDestiny(buyBo.getDestiny());
        buyResponseBo.setOrderStep(buyBo.getOrderStep());
        //插入控制表
        ControlUserDto controlUserDto = new ControlUserDto();
        controlUserDto.setOrderSeq(buyBo.getOrderSeq());
        controlUserDto.setOrderStep(buyBo.getOrderStep());
        controlUserDto.setRequestTime(formatTime(new Date()));
        controlUserDto.setUpdateTime(formatTime(new Date()));
        controlUserDto.setStepStatus("INIT");
        int i = controlUserController.addControlUser(controlUserDto);
        if (i != 1) {
            controlUserDto.setUpdateTime(formatTime(new Date()));
            controlUserDto.setStepStatus("PCFL");
            int j=controlUserController.updateControlUser(controlUserDto);
            buyResponseBo.setErrorDetail("插入用户控制表失败");
        } else {
            PositionBalanceDto position = positonBalanceController.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
            BigDecimal amout = position.getTotalVolume().subtract(buyBo.getTransactionAmount());
            position.setTotalVolume(amout);
            if (amout.compareTo(BigDecimal.ZERO) == 0){
                position.setPositionStatus("N");
            }
            int update = positonBalanceController.updatePosition(position);
            PositionBalanceDto newPosition = positonBalanceController.getPosition(buyBo.getProductId(), buyBo.getCustomerId());
            logger.info("用户持仓信息为："+position.toString());
            controlUserDto.setUpdateTime(formatTime(new Date()));
            controlUserDto.setStepStatus("PCSC");
            int j=controlUserController.updateControlUser(controlUserDto);
        }
        return buyResponseBo;
    }
}


