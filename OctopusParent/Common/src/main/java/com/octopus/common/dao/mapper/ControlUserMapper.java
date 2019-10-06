package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.ControlUserDto;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author gsj4877
 * @version 1.0.0
 * @date Created in 2019/10/6 9:51
 */
@Mapper
public interface ControlUserMapper {
    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param controlUserDto
     */
    @Options(useGeneratedKeys=true, keyProperty="orderSeq", keyColumn="order_seq")
    @Insert("INSERT INTO t_control_user (order_seq,request_time, update_time,order_step, order_status) VALUES (#{orderSeq},#{requestTime},#{updateTime},#{orderStep},#{orderStatus})")
    public int insert(ControlUserDto controlUserDto);

    /**
     * 更新操作
     *
     * @param controlUserDto
     * @return 受影响的行数
     */
    @Update("update t_control_user set request_time=#{requestTime},update_time=#{updateTime}, order_step=#{orderStep},order_status=#{orderStatus} where order_seq= #{orderSeq}")
    public int update(ControlUserDto controlUserDto);

    /**
     * 删除操作
     *
     * @param orderSeq
     * @return 受影响的行数
     */
    @Delete("delete from t_control_user where order_seq= #{orderSeq}")
    public int delete(@Param("orderSeq") BigInteger orderSeq);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select order_seq,request_time,update_time,order_step,order_status from t_control_user")
    @Results({
            @Result(property = "orderSeq",column = "order_seq"),
            @Result(property = "requestTime",column = "request_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "orderStep",column = "order_step"),
            @Result(property = "orderStatus",column = "order_status"),
    })
    public List<ControlUserDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param orderSeq
     * @return
     */
    @Select("select order_seq,request_time,update_time,order_step,order_status from t_control_user where order_seq=#{orderSeq}")
    @Results({
            @Result(property = "orderSeq",column = "order_seq"),
            @Result(property = "requestTime",column = "request_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "orderStep",column = "order_step"),
            @Result(property = "orderStatus",column = "order_status"),
    })
    public ControlUserDto selectById(@Param("orderSeq") BigInteger orderSeq);

}
