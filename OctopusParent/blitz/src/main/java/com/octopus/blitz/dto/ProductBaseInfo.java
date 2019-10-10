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
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "t_product_base_info")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ProductBaseInfo implements Serializable {

    @Getter @Setter
    @Id
    @Column(name = "product_id")
    private String productId;

    @Getter @Setter
    @Column(name = "product_name")
    private String productName;

}