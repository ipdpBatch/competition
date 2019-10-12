package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigInteger;

public class ControlCenterDto implements Serializable{
    
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
    //开关
    private String flag;
    
    
    
	public ControlCenterDto(BigInteger orderSeq, String requestTime, String updateTime, String orderStep,
                            String orderStatus, String flag) {
		super();
		this.orderSeq = orderSeq;
		this.requestTime = requestTime;
		this.updateTime = updateTime;
		this.orderStep = orderStep;
		this.orderStatus = orderStatus;
		this.flag = flag;
	}

    public ControlCenterDto() {

    }

    @Override
	public String toString() {
		return "ControlCenterDto [orderSeq=" + orderSeq + ", requestTime=" + requestTime + ", updateTime=" + updateTime
				+ ", orderStep=" + orderStep + ", orderStatus=" + orderStatus + ", flag=" + flag + "]";
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
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
    
	
}
