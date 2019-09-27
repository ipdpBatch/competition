package com.octopus.eureka.product.dao;


import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductBseInfoMapper {
    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param productBseInfoDto
     */
    @Insert("INSERT INTO t_product_base_info ( product_id, product_risk_level, product_type, product_raise_amount, product_remain_amount, product_name, registar_code, registar_name) VALUES (#{productId},#{productRiskLevel},#{productType},#{productRaiseAmount},#{productRemainAmount},#{productName},#{registarCode},#{registarName})")
    int insert(ProductBseInfoDto productBseInfoDto);

    /**
     * 更新操作
     *
     * @param productBseInfoDto
     * @return 受影响的行数
     */
    @Update("update t_product_base_info set product_id=#{productId},product_risk_level=#{productRiskLevel},product_type=#{productType},product_raise_amount=#{productRaiseAmount},product_remain_amount=#{productRemainAmount},product_name={productName},registar_code={registarCode},registar_name={registarName}  where product_id= #{productId}")
    int update(ProductBseInfoDto productBseInfoDto);

    /**
     * 删除操作
     *
     * @param id
     * @return 受影响的行数
     */
    @Delete("delete from t_product_base_info where product_id=#{productId}")
    Long delete(@Param("productId") String id);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_product_base_info")
    List<ProductBseInfoDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select * from t_product_base_info where product_id=#{productId}")
    ProductBseInfoDto selectById(@Param("productId") String id);

}
