package com.octopus.eureka.order.Dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019-09-26
 * @Version 1.0
 */
@Mapper
public interface ContolOrderMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param controlOrderDto
     */
    @Options(useGeneratedKeys=true, keyProperty="orderSeq", keyColumn="order_seq")
    @Insert("INSERT INTO t_control_order (request_time, update_time, order_step, step_status) VALUES (#{requestTime},#{updateTime},#{orderStep},#{stepStatus})")
    public int insert(ControlOrderDto controlOrderDto);

    /**
     * 更新操作
     *
     * @param controlOrderDto
     * @return 受影响的行数
     */
    @Update("update t_control_order set request_time=#{requestTime},update_time=#{updateTime},order_step=#{orderStep},step_status=#{stepStatus} where order_seq= #{orderSeq}")
    public int update(ControlOrderDto controlOrderDto);

    /**
     * 删除操作
     *
     * @param orderSeq
     * @return 受影响的行数
     */
    @Delete("delete from t_control_order where order_seq= #{orderSeq}")
    public int delete(@Param("orderSeq") String orderSeq);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select order_seq,request_time,update_time,order_step,step_status from t_control_order")
    public List<ControlOrderDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param orderSeq
     * @return
     */
    @Select("select * from t_control_order where order_seq=#{orderSeq}")
    public ControlOrderDto selectById(@Param("orderSeq") String orderSeq);


}
