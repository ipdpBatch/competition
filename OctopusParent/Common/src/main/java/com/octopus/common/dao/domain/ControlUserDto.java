package com.octopus.common.dao.domain;

import java.math.BigInteger;

/**
 * 文件创建时写入注释内容
 *
 * @author gsj4877
 * @version 1.0.0
 * @date Created in 2019/10/6 9:37
 */
public class ControlUserDto {
    //订单编号
    private BigInteger orderSeq;
    //请求时间
    private String requestTime;
    //更新时间
    private String updateTime;
    //订单步骤
    private String orderStep;
    //订单状态
    private String orderStatus;

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
        return orderStatus;
    }

    public void setStepStatus(String stepStatus) {
        this.orderStatus = stepStatus;
    }

    @Override
    public String toString() {
        return "ControlUserDto{" +
                "orderSeq=" + orderSeq +
                ", requestTime='" + requestTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", orderStep='" + orderStep + '\'' +
                ", stepStatus='" + orderStatus + '\'' +
                '}';
    }
}
