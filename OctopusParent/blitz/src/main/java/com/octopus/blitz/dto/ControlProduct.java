package com.octopus.blitz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "t_control_product")
@IdClass(ControlProductPrimaryKey.class)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ControlProduct implements Serializable {

    @Getter @Setter
    @Id
    @Column(name = "order_seq")
    private BigInteger orderSeq;

    @Getter @Setter
    @Column(name = "request_time")
    private String requestTime;

    @Getter @Setter
    @Column(name = "update_time")
    private String updateTime;

    @Getter @Setter
    @Column(name = "order_step")
    private String orderStep;

    @Getter @Setter
    @Column(name = "step_status")
    private String stepStatus;
}