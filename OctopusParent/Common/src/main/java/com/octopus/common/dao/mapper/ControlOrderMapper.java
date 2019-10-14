package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.ControlOrderDto;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019-09-26
 * @Version 1.0
 */
@Mapper
public interface ControlOrderMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param controlOrderDto
     */
    @Insert("INSERT INTO t_control_order (order_seq,request_time, update_time,order_step, step_status) VALUES (#{orderSeq},#{requestTime},#{updateTime},#{orderStep},#{stepStatus})")
    public int insert(ControlOrderDto controlOrderDto);

    /**
     * 更新操作
     *
     * @param controlOrderDto
     * @return 受影响的行数
     */
    @Update("<script>" + " update t_control_order set order_seq= #{orderSeq}  "
            + "<if test = \" requestTime != null and requestTime != '' \"> , request_time=#{requestTime} </if>"
            + "<if test = \" updateTime != null and updateTime != '' \"> , update_time=#{updateTime}</if>"
            + "<if test = \" orderStep != null and orderStep != '' \"> , order_step=#{orderStep}</if>"
            + "<if test = \" stepStatus != null and stepStatus != '' \"> , step_status=#{stepStatus} </if>"
            +" where order_seq= #{orderSeq} </script>")
    public int update(ControlOrderDto controlOrderDto);

    /**
     * 删除操作
     *
     * @param orderSeq
     * @return 受影响的行数
     */
    @Delete("delete from t_control_order where order_seq= #{orderSeq}")
    public int delete(@Param("orderSeq") BigInteger orderSeq);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_control_order")
    @Results({
            @Result(property = "orderSeq", column = "order_seq"),
            @Result(property = "requestTime", column = "request_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "orderStep", column = "order_step"),
            @Result(property = "stepStatus", column = "step_status")
    })
    public List<ControlOrderDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param orderSeq
     * @return
     */
    @Select("select * from t_control_order where order_seq=#{orderSeq}")
    @Results({
            @Result(property = "orderSeq", column = "order_seq"),
            @Result(property = "requestTime", column = "request_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "orderStep", column = "order_step"),
            @Result(property = "stepStatus", column = "step_status")
    })
    public ControlOrderDto selectById(@Param("orderSeq") BigInteger orderSeq);


}
