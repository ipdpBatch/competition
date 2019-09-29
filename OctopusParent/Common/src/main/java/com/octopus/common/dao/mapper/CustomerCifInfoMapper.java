package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.CustomerCifInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:11 2019/9/25
 */
@Mapper
public interface CustomerCifInfoMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param customerCifInfoDto
     */
    @Insert("INSERT INTO t_customer_cif_info ( customer_id, customer_name, certification_id, certification_type, risk_level, is_signed ) VALUES (#{customerId},#{customerName},#{certificationId},#{certificationType},#{riskLevel},#{isSigned})")
    int insert(CustomerCifInfoDto customerCifInfoDto);

    /**
     * 更新操作
     *
     * @param customerCifInfoDto
     * @return 受影响的行数
     */
    @Update("update t_customer_cif_info set customer_id=#{customerId},customer_name=#{customerName},certification_id=#{certificationId},certification_type=#{certificationType},risk_level=#{riskLevel},is_signed={isSigned}  where customer_id= #{customerId}")
    int update(CustomerCifInfoDto customerCifInfoDto);

    /**
     * 删除操作
     *
     * @param id
     * @return 受影响的行数
     */
    @Delete("delete from t_customer_cif_info where customer_id=#{customerId}")
    Long delete(@Param("customerId") String id);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_customer_cif_info")
    List<CustomerCifInfoDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select * from t_customer_cif_info where customer_id=#{customerId}")
    CustomerCifInfoDto selectById(@Param("customerId") String id);
}