package com.octopus.common.dao.mapper;

import java.util.List;

import com.octopus.common.dao.domain.CustomerSignInfoDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerSignInfoMapper {

    @Insert("INSERT INTO t_customer_sign_info ( customer_id, sign_status, transaction_date, transaction_time) VALUES (#{customerId},#{signStatus},#{transactionDate},#{transactionTime})")
    int insert(CustomerSignInfoDto customerSignInfoDto);

    @Update("update t_customer_sign_info set customer_id=#{customerId},sign_status=#{signStatus},transaction_date=#{transactionDate},transaction_time=#{transactionTime}  where customer_id= #{customerId}")
    int update(CustomerSignInfoDto customerSignInfoDto);

    @Delete("delete from t_customer_sign_info where customer_id=#{customerId}")
    Long delete(@Param("customerId") String customerId);

    @Select("select * from t_customer_sign_info")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "signStatus",column = "sign_status"),
            @Result(property = "transactionDate",column = "transaction_date"),
            @Result(property = "transactionTime",column = "transaction_time"),
    })
    List<CustomerSignInfoDto> selectAll();

    @Select("select * from t_customer_sign_info where customer_id=#{customerId}")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "signStatus",column = "sign_status"),
            @Result(property = "transactionDate",column = "transaction_date"),
            @Result(property = "transactionTime",column = "transaction_time"),
    })
    CustomerSignInfoDto selectByPrimaryKey(@Param("customerId") String customerId);

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
