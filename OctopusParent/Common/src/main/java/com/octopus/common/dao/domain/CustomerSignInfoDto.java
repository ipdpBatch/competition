package com.octopus.common.dao.domain;

import java.io.Serializable;

public class CustomerSignInfoDto implements Serializable{
    String customerId;
    String productId;
    String signStatus;
    String transcationDate;
    String transcationTime;

	public CustomerSignInfoDto(String customerId, String productId, String signStatus, String transcationDate,
			String transcationTime) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.signStatus = signStatus;
		this.transcationDate = transcationDate;
		this.transcationTime = transcationTime;
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
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public String getTranscationDate() {
		return transcationDate;
	}
	public void setTranscationDate(String transcationDate) {
		this.transcationDate = transcationDate;
	}
	public String getTranscationTime() {
		return transcationTime;
	}
	public void setTranscationTime(String transcationTime) {
		this.transcationTime = transcationTime;
	}
    
    
}
