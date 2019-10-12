package com.octopus.common.dao.mapper;

import java.util.List;

import com.octopus.common.dao.domain.CustomerSignInfoDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerSignInfoMapper {
    
	@Insert("INSERT INTO t_customer_sign__info ( customer_id, product_id, sign_status, transaction_date, transaction_time) VALUES (#{customerId},#{productId},#{signStatus},#{transactionDate},#{transactionTime})")
    int insert(CustomerSignInfoDto customerSignInfoDto);
	
	@Update("update t_customer_sign__info set customer_id=#{customerId},product_id=#{productId},sign_status=#{signStatus},transaction_date=#{transactionDate},transaction_time=#{transactionTime}  where customer_id= #{customerId} and product_id= #{productId}")
    int update(CustomerSignInfoDto customerSignInfoDto);
	
	@Delete("delete from t_customer_sign__info where customer_id=#{customerId} and product_id=#{productId}")
    Long delete(@Param("customerId") String customerId, @Param("productId") String productId);
    
    @Select("select * from t_customer_sign__info")
	List<CustomerSignInfoDto> selectAll();
	
    @Select("select * from t_customer_sign__info where customer_id=#{customerId} and product_id=#{productId}")
    CustomerSignInfoDto selectByPrimaryKey(@Param("customerId") String customerId, @Param("productId") String productId);

    @Select("select * from t_customer_sign_info where customer_id=#{customerId}")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "productId",column = "product_id"),
            @Result(property = "signStatus",column = "sign_status"),
            @Result(property = "transactionDate",column = "transaction_date"),
            @Result(property = "transactionTime",column = "transaction_time"),
    })
    CustomerSignInfoDto selectByCusid(@Param("customerId") String customerId);
}
