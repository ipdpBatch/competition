package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;


public class ProductQuotaInfo implements Serializable {

    private String productId;

    private BigDecimal totalVolume;

    private BigDecimal surplusVolume;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public BigDecimal getSurplusVolume() {
        return surplusVolume;
    }

    public void setSurplusVolume(BigDecimal surplusVolume) {
        this.surplusVolume = surplusVolume;
    }

    @Override
    public String toString() {
        return "ProductQuotaInfo{" +
                "productId='" + productId + '\'' +
                ", totalVolume=" + totalVolume +
                ", surplusVolume=" + surplusVolume +
                '}';
    }
}