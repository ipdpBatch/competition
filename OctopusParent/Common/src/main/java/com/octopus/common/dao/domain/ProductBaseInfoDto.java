package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:13 2019/9/25
 */
public class ProductBaseInfoDto implements Serializable {
    String productId;
    String productRiskLevel;
    String productType;
    BigDecimal productRaiseAmount;
    BigDecimal productRemainAmount;
    String productName;
    String registarCode;
    String registarName;


    public ProductBaseInfoDto(String productId, String productRiskLevel, String productType, BigDecimal productRaiseAmount,
                              BigDecimal productRemainAmount, String productName, String registarCode, String registarName) {
        this.productId = productId;
        this.productRiskLevel = productRiskLevel;
        this.productType = productType;
        this.productRaiseAmount = productRaiseAmount;
        this.productRemainAmount = productRemainAmount;
        this.productName = productName;
        this.registarCode = registarCode;
        this.registarName = registarName;
    }

    public ProductBaseInfoDto() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductRiskLevel() {
        return productRiskLevel;
    }

    public void setProductRiskLevel(String productRiskLevel) {
        this.productRiskLevel = productRiskLevel;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductRaiseAmount() {
        return productRaiseAmount;
    }

    public void setProductRaiseAmount(BigDecimal productRaiseAmount) {
        this.productRaiseAmount = productRaiseAmount;
    }

    public BigDecimal getProductRemainAmount() {
        return productRemainAmount;
    }

    public void setProductRemainAmount(BigDecimal productRemainAmount) {
        this.productRemainAmount = productRemainAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRegistarCode() {
        return registarCode;
    }

    public void setRegistarCode(String registarCode) {
        this.registarCode = registarCode;
    }

    public String getRegistarName() {
        return registarName;
    }

    public void setRegistarName(String registarName) {
        this.registarName = registarName;
    }

    @Override
    public String toString() {
        return "ProductBaseInfoDto{" +
                "productId='" + productId + '\'' +
                ", productRiskLevel='" + productRiskLevel + '\'' +
                ", productType='" + productType + '\'' +
                ", productRaiseAmount=" + productRaiseAmount +
                ", productRemainAmount=" + productRemainAmount +
                ", productName='" + productName + '\'' +
                ", registarCode='" + registarCode + '\'' +
                ", registarName='" + registarName + '\'' +
                '}';
    }
}
