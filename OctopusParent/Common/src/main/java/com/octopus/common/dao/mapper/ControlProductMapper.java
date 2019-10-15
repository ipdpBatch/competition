package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.ControlProductDto;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author lyn
 * @Date 2019-10-2
 * @Version 1.0
 */
@Mapper
public interface ControlProductMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param controlProductDto
     */
    @Insert("INSERT INTO t_control_product (order_seq,request_time, update_time,order_step, step_status) VALUES (#{orderSeq},#{requestTime},#{updateTime},#{orderStep},#{stepStatus})")
    public int insert(ControlProductDto controlProductDto);

    /**
     * 更新操作
     *
     * @param controlProductDto
     * @return 受影响的行数
     */
    @Update("update t_control_product set request_time=#{requestTime},update_time=#{updateTime},order_step=#{orderStep},step_status=#{stepStatus} where order_seq= #{orderSeq} and order_step=#{orderStep}")
    public int update(ControlProductDto controlProductDto);

    /**
     * 删除操作
     *
     * @param orderSeq
     * @return 受影响的行数
     */
    @Delete("delete from t_control_product where order_seq= #{orderSeq}")
    public int delete(@Param("orderSeq") BigInteger orderSeq);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_control_product")
    public List<ControlProductDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param orderSeq
     * @return
     */
    @Select("select * from t_control_product where order_seq=#{orderSeq}")
    public ControlProductDto selectById(@Param("orderSeq") BigInteger orderSeq);


}
