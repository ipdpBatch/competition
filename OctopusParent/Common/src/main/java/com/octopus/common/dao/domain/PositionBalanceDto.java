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
public class PositionBalanceDto implements Serializable {
    private String customerId;
    private  String productId;
    private BigDecimal totalVolume;
    private BigDecimal onTheWayVol;
    private BigDecimal onTheWayAmt;
    private String positionStatus;

    public PositionBalanceDto(String customerId, String productId, BigDecimal totalVolume, BigDecimal onTheWayVol, BigDecimal onTheWayAmt, String positionStatus) {
        this.customerId = customerId;
        this.productId = productId;
        this.totalVolume = totalVolume;
        this.onTheWayVol = onTheWayVol;
        this.onTheWayAmt = onTheWayAmt;
        this.positionStatus = positionStatus;
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

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public BigDecimal getOnTheWayVol() {
        return onTheWayVol;
    }

    public void setOnTheWayVol(BigDecimal onTheWayVol) {
        this.onTheWayVol = onTheWayVol;
    }

    public BigDecimal getOnTheWayAmt() {
        return onTheWayAmt;
    }

    public void setOnTheWayAmt(BigDecimal onTheWayAmt) {
        this.onTheWayAmt = onTheWayAmt;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }
}