package com.octopus.common.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 申购交易通过控制中心之后，转换为MQ信息，需要将此BO转成json传输给feignclient
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 20:35 2019/9/27
 */
public class BuyResponseBo implements Serializable {
    /*订单编号*/
    BigInteger orderSeq;
    /*业务代码*/
    String businessCode;
    /*客户号*/
    String customerId;
    /*产品代码*/
    String productId;
    /*交易金额*/
    BigDecimal transactionAmount;
    /*消息目标队列*/
    String destiny;
    /*订单步骤*/
    String orderStep;
    /*处理结果*/
    boolean orderReturnCode;
    /*失败结果*/
    String errorDetail;

    public BuyResponseBo() {

    }

    public boolean isOrderReturnCode() {
        return orderReturnCode;
    }

    public void setOrderReturnCode(boolean orderReturnCode) {
        this.orderReturnCode = orderReturnCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(String orderStep) {
        this.orderStep = orderStep;
    }

    public BigInteger getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(BigInteger orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BuyResponseBo(String businessCode, String customerId, String productId, BigDecimal transactionAmount) {
        this.businessCode = businessCode;
        this.customerId = customerId;
        this.productId = productId;
        this.transactionAmount = transactionAmount;
    }


    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }


}
