package com.octopus.common.dao.mapper;


import com.octopus.common.dao.domain.ProductBaseInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductBaseInfoMapper {
    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param productBaseInfoDto
     */
    @Insert("INSERT INTO t_product_base_info ( product_id, product_risk_level, product_type, product_raise_amount, product_remain_amount, product_name, registar_code, registar_name) VALUES (#{productId},#{productRiskLevel},#{productType},#{productRaiseAmount},#{productRemainAmount},#{productName},#{registarCode},#{registarName})")
    public int insert(ProductBaseInfoDto productBaseInfoDto);

    /**
     * 更新操作
     *
     * @param productBaseInfoDto
     * @return 受影响的行数
     */
    @Update("update t_product_base_info set product_id=#{productId},product_risk_level=#{productRiskLevel},product_type=#{productType},product_raise_amount=#{productRaiseAmount},product_remain_amount=#{productRemainAmount},product_name=#{productName},registar_code=#{registarCode},registar_name=#{registarName}  where product_id= #{productId}")
    public int update(ProductBaseInfoDto productBaseInfoDto);

    /**
     * 删除操作
     *
     * @param id
     * @return 受影响的行数
     */
    @Delete("delete from t_product_base_info where product_id=#{productId}")
    public Long delete(@Param("productId") String id);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_product_base_info")
    @Results({
            @Result(property = "productId",column = "product_id"),
            @Result(property = "productRiskLevel",column = "product_risk_level"),
            @Result(property = "productType",column = "product_type"),
            @Result(property = "productRaiseAmount",column = "product_raise_amount"),
            @Result(property = "productRemainAmount",column = "product_remain_amount"),
            @Result(property = "productName",column = "product_name"),
            @Result(property = "registarCode",column = "registar_code"),
            @Result(property = "registarName",column = "registar_name")
    })
    public List<ProductBaseInfoDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select * from t_product_base_info where product_id=#{productId}")
    @Results({
            @Result(property = "productId",column = "product_id"),
            @Result(property = "productRiskLevel",column = "product_risk_level"),
            @Result(property = "productType",column = "product_type"),
            @Result(property = "productRaiseAmount",column = "product_raise_amount"),
            @Result(property = "productRemainAmount",column = "product_remain_amount"),
            @Result(property = "productName",column = "product_name"),
            @Result(property = "registarCode",column = "registar_code"),
            @Result(property = "registarName",column = "registar_name")
    })
    public ProductBaseInfoDto selectById(@Param("productId") String id);

}
