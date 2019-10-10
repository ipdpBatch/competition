package com.octopus.blitz.repository;

import com.octopus.blitz.dto.ProductBaseInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product-base-info", path = "product-base-info")
public interface ProductBaseInfoRepository extends JpaRepository<ProductBaseInfo, String> {
    String CACHE_KEY = "product-quota-cache";

    @Cacheable(cacheNames = CACHE_KEY, key = "#productId")
    ProductBaseInfo getByProductId(String productId);
}