package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author dongjiale
 * @Date 2019/9/26 11:35 PM
 * @Version 1.0
 */
public class OrderFinancialDto implements Serializable{
    //订单编号
    private double orderSeq;
    //建单日期
    private String createDate;
    //建单时间
    private String createTime;
    //交易码
    private String transactionCode;
    //客户编号
    private String customerId;
    //产品编号
    private String productId;
    //交易金额
    private BigDecimal transactionAmount;
    //交易份额
    private BigDecimal transactionVol;
    //订单状态
    private String orderStatus;
    //订单资金状态
    private String capitalStatus;

    public double getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(double orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
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

    public BigDecimal getTransactionVol() {
        return transactionVol;
    }

    public void setTransactionVol(BigDecimal transactionVol) {
        this.transactionVol = transactionVol;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCapitalStatus() {
        return capitalStatus;
    }

    public void setCapitalStatus(String capitalStatus) {
        this.capitalStatus = capitalStatus;
    }

    @Override
    public String toString() {
        return "OrderFinancialDto{" +
                "orderSeq='" + orderSeq + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionVol=" + transactionVol +
                ", orderStatus='" + orderStatus + '\'' +
                ", capitalStatus='" + capitalStatus + '\'' +
                '}';
    }
}
