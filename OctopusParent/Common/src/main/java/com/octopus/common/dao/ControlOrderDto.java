package com.octopus.common.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author dongjiale
 * @Date 2019-09-26
 * @Version 1.0
 */
public class ControlOrderDto implements Serializable{
    //订单编号
    private String orderSeq;
    //请求时间
    private Date requestTime;
    //更新时间
    private Date updateTime;
    //订单步骤
    private String orderStep;
    //订单状态
    private String stepStatus;

    public ControlOrderDto() {
    }

    public ControlOrderDto(String orderSeq, Date requestTime, Date updateTime, String orderStep, String stepStatus) {
        this.orderSeq = orderSeq;
        this.requestTime = requestTime;
        this.updateTime = updateTime;
        this.orderStep = orderStep;
        this.stepStatus = stepStatus;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
