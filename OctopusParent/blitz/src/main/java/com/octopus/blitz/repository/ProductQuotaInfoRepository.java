package com.octopus.blitz.repository;

import com.octopus.blitz.dto.ProductQuotaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.LockModeType;
import java.math.BigDecimal;

@RepositoryRestResource(collectionResourceRel = "product-quota-info", path = "product-quota-info")
public interface ProductQuotaInfoRepository extends JpaRepository<ProductQuotaInfo, String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select t from ProductQuotaInfo t where t.productId =?1 ")
    ProductQuotaInfo getOneForUpdate(@Param("productId") String productId);

    @Modifying
    @Query("update ProductQuotaInfo t set t.surplusVolume = t.surplusVolume - ?2 where t.productId =?1")
    void substractSurplusVolume(@Param("productId") String productId, @Param("amount") BigDecimal amount);

    //@Lock 作用的for update作用一样，将此行数据进行加锁，当整个方法将事务提交后，才会解锁
//    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
//    @Query(value = "select t from Course t where t.id =?1 ")
//    ProductQuotaInfo queryAllById( Integer courseId);

    //将course表中的electiveNum进行加1操作
//    @Modifying
//    @Query("update Course t set t.electiveNum = t.electiveNum + 1 where t.id =?1")
//    void addElectiveNumByCourseId(Integer courseId);

}