package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author dongjiale
 * @Date 2019/9/26 11:35 PM
 * @Version 1.0
 */
public class OrderFinancialDto implements Serializable{
    //订单编号
    private String orderSeq;
    //建单日期
    private String createDate;
    //建单时间
    private String createTime;
    //交易码
    private String transcationCode;
    //客户编号
    private String customerId;
    //产品编号
    private String productId;
    //交易金额
    private BigDecimal transcationAmout;
    //交易份额
    private BigDecimal transcationVol;
    //订单状态
    private String orderStatus;
    //订单资金状态
    private String capitalStatus;

    public OrderFinancialDto() {
    }

    public OrderFinancialDto(String orderSeq, String createDate, String createTime, String transcationCode, String customerId, String productId, BigDecimal transcationAmout, BigDecimal transcationVol, String orderStatus, String capitalStatus) {
        this.orderSeq = orderSeq;
        this.createDate = createDate;
        this.createTime = createTime;
        this.transcationCode = transcationCode;
        this.customerId = customerId;
        this.productId = productId;
        this.transcationAmout = transcationAmout;
        this.transcationVol = transcationVol;
        this.orderStatus = orderStatus;
        this.capitalStatus = capitalStatus;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
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

    public String getTranscationCode() {
        return transcationCode;
    }

    public void setTranscationCode(String transcationCode) {
        this.transcationCode = transcationCode;
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

    public BigDecimal getTranscationAmout() {
        return transcationAmout;
    }

    public void setTranscationAmout(BigDecimal transcationAmout) {
        this.transcationAmout = transcationAmout;
    }

    public BigDecimal getTranscationVol() {
        return transcationVol;
    }

    public void setTranscationVol(BigDecimal transcationVol) {
        this.transcationVol = transcationVol;
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
                ", transcationCode='" + transcationCode + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", transcationAmout=" + transcationAmout +
                ", transcationVol=" + transcationVol +
                ", orderStatus='" + orderStatus + '\'' +
                ", capitalStatus='" + capitalStatus + '\'' +
                '}';
    }
}
