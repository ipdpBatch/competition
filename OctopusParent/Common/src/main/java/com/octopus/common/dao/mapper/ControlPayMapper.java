package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.ControlPayDto;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019-09-26
 * @Version 1.0
 */
@Mapper
public interface ControlPayMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param controlPayDto
     */
    @Insert("INSERT INTO t_control_pay (order_seq,request_time, update_time,order_step, step_status) VALUES (#{orderSeq},#{requestTime},#{updateTime},#{orderStep},#{stepStatus})")
    public int insert(ControlPayDto controlPayDto);

    /**
     * 更新操作
     *
     * @param controlPayDto
     * @return 受影响的行数
     */
    @Update("update t_control_pay set request_time=#{requestTime},update_time=#{updateTime}, order_step=#{orderStep},step_status=#{stepStatus} where order_seq= #{orderSeq}")
    public int update(ControlPayDto controlPayDto);

    /**
     * 删除操作
     *
     * @param orderSeq
     * @return 受影响的行数
     */
    @Delete("delete from t_control_pay where order_seq= #{orderSeq}")
    public int delete(@Param("orderSeq") BigInteger orderSeq);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select order_seq, date_format(request_time,'%y%m%d'), date_format(update_time,'%y%m%d'), order_step, step_status from t_control_pay")
    @Results({
            @Result(property = "orderSeq",column = "order_seq"),
            @Result(property = "requestTime",column = "request_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "orderStep",column = "order_step"),
            @Result(property = "stepStatus",column = "step_status")
    })
    public List<ControlPayDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param orderSeq
     * @return
     */
    @Select("select order_seq,date_format(request_time,'%y%m%d'), date_format(update_time,'%y%m%d'), order_step,step_status from t_control_pay where order_seq=#{orderSeq}")
    @Results({
            @Result(property = "orderSeq",column = "order_seq"),
            @Result(property = "requestTime",column = "request_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "orderStep",column = "order_step"),
            @Result(property = "stepStatus",column = "step_status")
    })
    public ControlPayDto selectById(@Param("orderSeq") BigInteger orderSeq);


}
