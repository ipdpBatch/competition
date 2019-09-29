package com.octopus.common.dao.mapper;

import java.util.List;

import com.octopus.common.dao.domain.CustomerSignInfoDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerSignInfoMapper {
    
	@Insert("INSERT INTO t_customer_sign__info ( customer_id, product_id, sign_status, transcation_date, transcation_time) VALUES (#{customerId},#{productId},#{signStatus},#{transcationDate},#{transcationTime})")
    int insert(CustomerSignInfoDto customerSignInfoDto);
	
	@Update("update t_customer_sign__info set customer_id=#{customerId},product_id=#{productId},sign_status=#{signStatus},transcation_date=#{transcationDate},transcation_time=#{transcationTime}  where customer_id= #{customerId} and product_id= #{productId}")
    int update(CustomerSignInfoDto customerSignInfoDto);
	
	@Delete("delete from t_customer_sign__info where customer_id=#{customerId} and product_id=#{productId}")
    Long delete(@Param("customerId") String customerId, @Param("productId") String productId);
    
    @Select("select * from t_customer_sign__info")
	List<CustomerSignInfoDto> selectAll();
	
    @Select("select * from t_customer_sign__info where customer_id=#{customerId} and product_id=#{productId}")
    CustomerSignInfoDto selectByPrimaryKey(@Param("customerId") String customerId, @Param("productId") String productId);

    @Select("select * from t_customer_sign__info where customer_id=#{customerId}")
    CustomerSignInfoDto selectByCusid(@Param("customerId") String customerId);
}
