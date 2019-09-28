package com.octopus.eureka.pay.dao;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:13 2019/9/25
 */
public class PaymentInfoDto implements Serializable {

    public float getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(float orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNoOut() {
        return accountNoOut;
    }

    public void setAccountNoOut(String accountNoOut) {
        this.accountNoOut = accountNoOut;
    }

    public String getAccountNoIn() {
        return accountNoIn;
    }

    public void setAccountNoIn(String accountNoIn) {
        this.accountNoIn = accountNoIn;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPaySystem() {
        return paySystem;
    }

    public void setPaySystem(String paySystem) {
        this.paySystem = paySystem;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    float orderSeq;
    String  payType;
    String customerId;
    String accountNoOut;
    String accountNoIn;

    public PaymentInfoDto(float orderSeq, String payType, String customerId, String accountNoOut, String accountNoIn, BigDecimal payAmount, String paySystem, String returnCode, String errorMessage) {
        this.orderSeq = orderSeq;
        this.payType = payType;
        this.customerId = customerId;
        this.accountNoOut = accountNoOut;
        this.accountNoIn = accountNoIn;
        this.payAmount = payAmount;
        this.paySystem = paySystem;
        this.returnCode = returnCode;
        this.errorMessage = errorMessage;
    }

    BigDecimal payAmount;
    String paySystem;
    String returnCode;
    String errorMessage;

}
