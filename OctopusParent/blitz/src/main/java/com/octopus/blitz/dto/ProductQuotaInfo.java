package com.octopus.blitz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "t_product_quota_info")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ProductQuotaInfo implements Serializable {

    @Getter @Setter
    @Id
    @Column(name = "product_id")
    private String productId;

    @Getter @Setter
    @Column(name = "total_volume")
    private BigDecimal totalVolume;

    @Getter @Setter
    @Column(name = "surplus_volume")
    private BigDecimal surplusVolume;
}