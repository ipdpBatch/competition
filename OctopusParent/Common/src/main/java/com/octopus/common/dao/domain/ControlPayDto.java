package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author dongjiale
 * @Date 2019-09-26
 * @Version 1.0
 */
public class ControlPayDto implements Serializable{
    //订单编号
     BigInteger orderSeq;
    //请求时间
     String requestTime;
    //更新时间
    String updateTime;
//    //订单步骤
     String orderStep;
//    //订单状态
     String stepStatus;


    public ControlPayDto(BigInteger orderSeq, String requestTime, String updateTime, String orderStep, String stepStatus) {
        this.orderSeq = orderSeq;
        this.requestTime = requestTime;
        this.updateTime = updateTime;
        this.orderStep = orderStep;
        this.stepStatus = stepStatus;
    }
    public ControlPayDto(){

    }

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
        return "ControlOrderDto{" +
                "orderSeq='" + orderSeq + '\'' +
                ", requestTime=" + requestTime +
                ", updateTime=" + updateTime +
                ", orderStep='" + orderStep + '\'' +
                ", stepStatus='" + stepStatus + '\'' +
                '}';
    }
}
