package com.octopus.blitz.repository;

import com.octopus.blitz.dto.ProductBaseInfo;
import com.octopus.blitz.dto.ProductQuotaInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.LockModeType;
import java.math.BigDecimal;

@RepositoryRestResource(collectionResourceRel = "product-base-info", path = "product-base-info")
public interface ProductBaseInfoRepository extends JpaRepository<ProductBaseInfo, String> {
    String CACHE_KEY = "product-quota-cache";

    @Cacheable(cacheNames = CACHE_KEY, key = "#productId")
    ProductBaseInfo getByProductId(String productId);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select t from ProductBaseInfo t where t.productId =?1 ")
    ProductQuotaInfo getOneForUpdate(@Param("productId") String productId);

    @Modifying
    @Query("update ProductBaseInfo t set t.productRemainAmount = t.productRemainAmount - ?2 where t.productId =?1")
    void substractSurplusVolume(@Param("productId") String productId, @Param("amount") BigDecimal amount);

}