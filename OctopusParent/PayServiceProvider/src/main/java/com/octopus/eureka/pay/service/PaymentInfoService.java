package com.octopus.eureka.pay.service;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlPayDto;
import com.octopus.common.dao.domain.PayAccountDto;
import com.octopus.common.dao.domain.PaymentInfoDto;
import com.octopus.common.dao.mapper.ControlPayMapper;
import com.octopus.common.dao.mapper.PayAccountMapper;
import com.octopus.common.dao.mapper.PaymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;

import static com.octopus.common.utils.DateUtil.formatTime;

@Component
public class PaymentInfoService {
    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PaymentInfoService.class);

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private PayAccountMapper payAccountMapper;

    @Autowired
    private ControlPayMapper controlPayMapper;

    public BuyResponseBo processPay(BuyBo buyBo) {
        ControlPayDto controlPayDto = new ControlPayDto();
        controlPayDto.setOrderStep(buyBo.getOrderStep());
        controlPayDto.setRequestTime(formatTime(new Date()));
        controlPayDto.setOrderSeq(buyBo.getOrderSeq());
        controlPayDto.setStepStatus("INIT");
        controlPayDto.setUpdateTime(formatTime(new Date()));
        //int control=controlPayMapper.insert(controlPayDto);
        //封装返回Bo
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        buyResponseBo.setBusinessCode(buyBo.getBusinessCode());
        buyResponseBo.setCustomerId(buyBo.getCustomerId());
        buyResponseBo.setDestiny(buyBo.getDestiny());
        buyResponseBo.setOrderSeq(buyBo.getOrderSeq());
        buyResponseBo.setProductId(buyBo.getProductId());
        buyResponseBo.setTransactionAmount(buyBo.getTransactionAmount());
        buyResponseBo.setOrderStep(buyBo.getOrderStep());
        PayAccountDto payAccountDto = new PayAccountDto();
        PaymentInfoDto paymentInfoDto = new PaymentInfoDto();
        payAccountDto =  payAccountMapper.selectById(buyBo.getCustomerId());

        switch (buyBo.getBusinessCode()){
            case "020":
                //认购-支付类型为付款
                paymentInfoDto.setPayType("1");
                paymentInfoDto.setAccountNoOut(payAccountDto.getAccountNo());
                paymentInfoDto.setAccountNoIn("1");
                break;
            case "022":
                //申购-支付类型为付款
                paymentInfoDto.setPayType("1");
                paymentInfoDto.setAccountNoOut(payAccountDto.getAccountNo());
                paymentInfoDto.setAccountNoIn("1");
                break;
            case "024":
                //赎回-支付类型为冲正
                paymentInfoDto.setPayType("2");
                paymentInfoDto.setAccountNoIn(payAccountDto.getAccountNo());
                paymentInfoDto.setAccountNoOut("1");
                break;
            default:
                //其他-支付类型为9-未知。
                paymentInfoDto.setPayType("9");
                paymentInfoDto.setAccountNoIn("9");
                paymentInfoDto.setAccountNoOut("9");
                break;
        }

        paymentInfoDto.setCustomerId(buyBo.getCustomerId());
        paymentInfoDto.setOrderSeq(buyBo.getOrderSeq());

        if (!("1".equals(payAccountDto.getStatus()))){
            paymentInfoDto.setErrorMessage("账户状态异常");
            paymentInfoDto.setReturnCode("002");
            paymentInfoDto.setPayAmount(BigDecimal.valueOf(0));

            int insertResult = paymentInfoMapper.insert(paymentInfoDto);
            if(insertResult == 1){
                buyResponseBo.setOrderReturnCode(true);
                controlPayDto.setStepStatus("PCSC");
                controlPayDto.setUpdateTime(formatTime(new Date()));
                int controlResult=controlPayMapper.insert(controlPayDto);
            }else {
                buyResponseBo.setOrderReturnCode(false);
                controlPayDto.setStepStatus("PCFL");
                controlPayDto.setUpdateTime(formatTime(new Date()));
                int controlResult=controlPayMapper.insert(controlPayDto);
            }
            return buyResponseBo;
        }
        if("1".equals(paymentInfoDto.getPayType())){
            if (-1==(payAccountDto.getBal().compareTo(buyBo.getTransactionAmount()))){
                paymentInfoDto.setErrorMessage("余额不足");
                paymentInfoDto.setReturnCode("001");
                paymentInfoDto.setPayAmount(BigDecimal.valueOf(0));

                int insertResult = paymentInfoMapper.insert(paymentInfoDto);
                if(insertResult == 1){
                    buyResponseBo.setOrderReturnCode(true);
                    controlPayDto.setStepStatus("PCSC");
                    controlPayDto.setUpdateTime(formatTime(new Date()));
                    int controlResult=controlPayMapper.insert(controlPayDto);
                }else {
                    buyResponseBo.setOrderReturnCode(false);
                    controlPayDto.setStepStatus("PCFL");
                    controlPayDto.setUpdateTime(formatTime(new Date()));
                    int controlResult=controlPayMapper.insert(controlPayDto);
                }
                return buyResponseBo;

            }
            else
            {
                payAccountDto.setBal(payAccountDto.getBal().subtract(buyBo.getTransactionAmount()));
            }
        }
        if ("2".equals(paymentInfoDto.getPayType())){
            payAccountDto.setBal(payAccountDto.getBal().add(buyBo.getTransactionAmount()));
        }

        paymentInfoDto.setPayAmount(buyBo.getTransactionAmount());
        int accountResult = payAccountMapper.update(payAccountDto);;
        if(accountResult == 1){
            buyResponseBo.setOrderReturnCode(true);
        }else {
            buyResponseBo.setOrderReturnCode(false);
            paymentInfoDto.setErrorMessage("余额更新异常");
            paymentInfoDto.setReturnCode("0004");
            controlPayDto.setStepStatus("PCFL");
            controlPayDto.setUpdateTime(formatTime(new Date()));
            int controlResult=controlPayMapper.insert(controlPayDto);

            return buyResponseBo;
        }
        paymentInfoDto.setErrorMessage("交易完成");
        paymentInfoDto.setReturnCode("0000");
        buyResponseBo.setErrorDetail(paymentInfoDto.getErrorMessage());

        int insertResult = paymentInfoMapper.insert(paymentInfoDto);;
        if(insertResult == 1){
            buyResponseBo.setOrderReturnCode(true);
        }else {
            buyResponseBo.setOrderReturnCode(false);
        }
        controlPayDto.setStepStatus("PCSC");
        controlPayDto.setUpdateTime(formatTime(new Date()));
        int controlResult=controlPayMapper.insert(controlPayDto);
        return buyResponseBo;
    }
}
