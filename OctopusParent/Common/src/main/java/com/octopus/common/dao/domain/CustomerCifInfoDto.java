package com.octopus.common.dao.domain;

import java.io.Serializable;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:13 2019/9/25
 */
public class CustomerCifInfoDto implements Serializable {
    String customerId;
    String customerName;
    String certificationId;
    String certificationType;
    String riskLevel;
    String isSigned;


    public CustomerCifInfoDto(String customerId, String customerName, String certificationId, String certificationType, String riskLevel, String isSigned) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.certificationId = certificationId;
        this.certificationType = certificationType;
        this.riskLevel = riskLevel;
        this.isSigned = isSigned;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
    }

    public String getCertificationType() {
        return certificationType;
    }

    public void setCertificationType(String certificationType) {
        this.certificationType = certificationType;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(String isSigned) {
        this.isSigned = isSigned;
    }

}
