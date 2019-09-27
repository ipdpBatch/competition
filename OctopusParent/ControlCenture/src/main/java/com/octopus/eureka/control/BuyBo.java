package com.octopus.eureka.control;

import java.math.BigDecimal;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 20:35 2019/9/27
 */
class BuyBo {
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

    String businessCode;
    String customerId;
    String productId;
    BigDecimal transactionAmount;


}
