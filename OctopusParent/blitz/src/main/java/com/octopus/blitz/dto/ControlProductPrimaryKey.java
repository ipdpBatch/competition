package com.octopus.blitz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
public class ControlProductPrimaryKey implements Serializable {

    @Getter @Setter
    @Id
    @Column(name = "order_seq")
    private BigInteger orderSeq;

    @Getter @Setter
    @Column(name = "order_step")
    private String orderStep;
}