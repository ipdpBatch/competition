package com.octopus.common.dao.domain;

import java.io.Serializable;

/**
 * @Author dongjiale
 * @Date 2019-09-26
 * @Version 1.0
 */
public class OrderTestDto implements Serializable{
    //订单编号
    private String orderId;
    //
    private String orderName;

    public OrderTestDto() {

    }

    public OrderTestDto(String orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
