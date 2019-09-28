package com.octopus.eureka.user.dao;

import java.io.Serializable;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:13 2019/9/25
 */
public class PositionBalanceDto<Decimal> implements Serializable {
    String customerId;
    String productId;
    Decimal totalVolume;
    Decimal onTheWayVol;
    Decimal onTheWayAmt;
    String positionStatus;

    public PositionBalanceDto(String customerId, String productId, Decimal totalVolume, Decimal onTheWayVol, Decimal onTheWayAmt, String positionStatus) {
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

    public Decimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Decimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Decimal getOnTheWayVol() {
        return onTheWayVol;
    }

    public void setOnTheWayVol(Decimal onTheWayVol) {
        this.onTheWayVol = onTheWayVol;
    }

    public Decimal getOnTheWayAmt() {
        return onTheWayAmt;
    }

    public void setOnTheWayAmt(Decimal onTheWayAmt) {
        this.onTheWayAmt = onTheWayAmt;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }
}