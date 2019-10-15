package com.octopus.blitz.repository;

import com.octopus.blitz.dto.ControlProduct;
import com.octopus.blitz.dto.ControlProductPrimaryKey;
import com.octopus.blitz.dto.ProductQuotaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.math.BigInteger;

@RepositoryRestResource(collectionResourceRel = "control-product", path = "control-product")
public interface ControlProductRepository extends JpaRepository<ControlProduct, ControlProductPrimaryKey> {

}