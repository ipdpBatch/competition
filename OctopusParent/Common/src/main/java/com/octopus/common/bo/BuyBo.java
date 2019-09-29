package com.octopus.common.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 申购交易通过控制中心之后，转换为MQ信息，需要将此BO转成json传输给feignclient
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 20:35 2019/9/27
 */
public class BuyBo implements Serializable {
    /*订单编号*/
    float orderSeq;
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

    public String getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(String orderStep) {
        this.orderStep = orderStep;
    }

    public BuyBo() {
        //
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

    public BuyBo(String businessCode, String customerId, String productId, BigDecimal transactionAmount) {
        this.businessCode = businessCode;
        this.customerId = customerId;
        this.productId = productId;
        this.transactionAmount = transactionAmount;
    }

    public float getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(float orderSeq) {
        this.orderSeq = orderSeq;
    }



    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }


}
