package com.octopus.common.dao.domain;

import java.io.Serializable;

public class ControlCentureDto implements Serializable{
    
	//订单编号
    private String orderSeq;
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
    
	@Override
	public String toString() {
		return "ControlCentureDto [orderSeq=" + orderSeq + ", requestTime=" + requestTime + ", updateTime=" + updateTime
				+ ", orderStep=" + orderStep + ", orderStatus=" + orderStatus + ", flag=" + flag + ", getOrderSeq()="
				+ getOrderSeq() + ", getRequestTime()=" + getRequestTime() + ", getUpdateTime()=" + getUpdateTime()
				+ ", getOrderStep()=" + getOrderStep() + ", getOrderStatus()=" + getOrderStatus() + ", getFlag()="
				+ getFlag() + "]";
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
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
