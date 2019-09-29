package com.octopus.common.dao.mapper;


import com.octopus.common.dao.domain.OrderTestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderTestMapper {

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_order_test")
    List<OrderTestDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select * from t_order_test where order_id=#{orderId}")
    OrderTestDto selectById(@Param("orderId") String id);

}
