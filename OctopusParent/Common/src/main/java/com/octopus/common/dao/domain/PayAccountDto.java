package com.octopus.common.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:13 2019/9/25
 */
public class PayAccountDto implements Serializable {

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getBal() {
        return bal;
    }

    public void setBal(BigDecimal bal) {
        this.bal = bal;
    }

    public BigDecimal getFreezBal() {
        return freezBal;
    }

    public void setFreezBal(BigDecimal freezBal) {
        this.freezBal = freezBal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    String customerId;
    String accountNo;
    BigDecimal bal;
    BigDecimal freezBal;
    String status;

    public PayAccountDto() {
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.bal = bal;
        this.freezBal = freezBal;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PayAccountDto{" +
                "customerId='" + customerId + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", bal='" + bal + '\'' +
                ", freezBal=" + freezBal +
                ", status=" + status +
                '}';
    }

}
