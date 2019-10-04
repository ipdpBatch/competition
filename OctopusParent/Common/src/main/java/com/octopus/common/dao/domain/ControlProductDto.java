package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author lyn
 * @Date 2019-10-2
 * @Version 1.0
 */
public class ControlProductDto implements Serializable{
    //订单编号
    private BigInteger orderSeq;
    //请求时间
    private String requestTime;
    //更新时间
    private String updateTime;
//    //订单步骤
    private String orderStep;
//    //订单状态
    private String stepStatus;


    public ControlProductDto(BigInteger orderSeq, String requestTime, String updateTime, String orderStep, String stepStatus) {
        this.orderSeq = orderSeq;
        this.requestTime = requestTime;
        this.updateTime = updateTime;
        this.orderStep = orderStep;
        this.stepStatus = stepStatus;
    }

    public ControlProductDto() {

    }

//    public ControlProductDto() {
//
//    }

    public BigInteger getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(BigInteger orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(String orderStep) {
        this.orderStep = orderStep;
    }

    public String getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(String stepStatus) {
        this.stepStatus = stepStatus;
    }

    @Override
    public String toString() {
        return "ControlProductDto{" +
                "orderSeq='" + orderSeq + '\'' +
                ", requestTime=" + requestTime +
                ", updateTime=" + updateTime +
                ", orderStep='" + orderStep + '\'' +
                ", stepStatus='" + stepStatus + '\'' +
                '}';
    }
}
