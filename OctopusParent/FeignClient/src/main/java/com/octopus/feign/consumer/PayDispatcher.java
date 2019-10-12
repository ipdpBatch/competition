package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlPayDto;
import com.octopus.common.dao.domain.PayAccountDto;
import com.octopus.common.dao.domain.PaymentInfoDto;
import com.octopus.feign.consumer.provider.PayClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


@RestController
public class PayDispatcher {
    //    @Autowired
//    private HomeClient homeClient;
    private final static Logger logger = LoggerFactory.getLogger(PayDispatcher.class);
    @Autowired
    private PayClient payClient;

    //订单控制表
    public List<ControlPayDto> getControlPayList() {
        return payClient.getControlPayList();
    }

    public ControlPayDto getControlPay(BigInteger orderSeq) {
        return payClient.getControlPay(orderSeq);
    }

    public int deleteControlPay(BigInteger orderSeq) {
        return payClient.deleteControlPay(orderSeq);
    }

    public int updateControlPay(ControlPayDto controlPayDto) {
        return payClient.updateControlPay(controlPayDto);
    }

    public int addControlPay(ControlPayDto controlPayDto) {
        return payClient.addControlPay(controlPayDto);
    }


    //订单控制表
    public List<PaymentInfoDto> getPayList() {
        return payClient.getPayList();
    }

    public PaymentInfoDto getPay(BigInteger orderSeq, String payType) {
        return payClient.getPay(orderSeq, payType);
    }

    public int deletePay(BigInteger orderSeq, String payType) {
        return payClient.deletePay(orderSeq, payType);
    }

    public int updatePay(PaymentInfoDto paymentInfoDto) {
        return payClient.updatePay(paymentInfoDto);
    }

    public int addPay(PaymentInfoDto paymentInfoDto) {
        return payClient.addPay(paymentInfoDto);
    }

    public List<PayAccountDto> getPayaccountList() {
        return payClient.getPayaccountList();
    }

    public PayAccountDto getPayaccount(String customerId) {
        return payClient.getPayaccount(customerId);
    }

    public int deletePayaccount(String customerId) {
        return payClient.deletePayaccount(customerId);
    }

    public int updatePayaccount(PayAccountDto payAccountDto) {
        return payClient.updatePayaccount(payAccountDto);
    }

    public int addPayaccount(PayAccountDto payAccountDto) {
        return payClient.addPayaccount(payAccountDto);
    }

    /*订单建单服务*/
    public BuyResponseBo payProcess(BuyBo buyBo) {
        logger.info("PayDispatcher层请求申购扣款，请求参数：" + buyBo.toString());
        BuyResponseBo buyResponseBo = payClient.processPay(buyBo);
        if (!buyResponseBo.isOrderReturnCode()){
            logger.error("PayDispatcher层请求申购扣款失败" + buyResponseBo.getErrorDetail());
        }
        return buyResponseBo;
    }


}