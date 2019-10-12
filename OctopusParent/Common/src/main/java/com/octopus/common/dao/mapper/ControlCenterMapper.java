package com.octopus.common.dao.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.*;
import com.octopus.common.dao.domain.ControlCenterDto;

@Mapper
public interface ControlCenterMapper {
     /**
      * 插入
      * @param controlCenterDto
      * @return
      */
	 @Options(useGeneratedKeys=true, keyProperty="orderSeq", keyColumn="order_seq")
	 @Insert("INSERT INTO t_control_center (order_seq,request_time, update_time,order_step, order_status, flag) VALUES (#{orderSeq},#{requestTime},#{updateTime},#{orderStep},#{orderStatus},#{flag})")
	 public int insert(ControlCenterDto controlCenterDto);
	 
	 /**
	  * 更新
	  * @param controlCenterDto
	  * @return
	  */
	 @Update("update t_control_center set request_time=#{requestTime},update_time=#{updateTime}, order_step=#{orderStep},order_status=#{orderStatus}, flag=#{flag} where order_seq= #{orderSeq}")
	 public int update(ControlCenterDto controlCenterDto);
	 
	 /**
	  * 删除
	  * @param orderSeq
	  * @return
	  */
	 @Delete("delete from t_control_center where order_seq= #{orderSeq}")
	 public int delete(@Param("orderSeq") BigInteger orderSeq);
	 
	 
	 /**
	  * 查询所有
	  * @return
	  */
	 @Select("select * from t_control_center")
	 @Results({
			 @Result(property = "orderSeq",column = "order_seq"),
			 @Result(property = "requestTime",column = "request_time"),
			 @Result(property = "updateTime",column = "update_time"),
			 @Result(property = "orderStep",column = "order_step"),
			 @Result(property = "orderStatus",column = "order_status"),
			 @Result(property = "flag",column = "flag"),
	 })
	 public List<ControlCenterDto> selectAll();
	 
	 
	 /**
	  * 查询单条
	  * @param orderSeq
	  * @return
	  */
	 @Select("select * from t_control_center where order_seq=#{orderSeq}")
	 @Results({
			 @Result(property = "orderSeq",column = "order_seq"),
			 @Result(property = "requestTime",column = "request_time"),
			 @Result(property = "updateTime",column = "update_time"),
			 @Result(property = "orderStep",column = "order_step"),
			 @Result(property = "orderStatus",column = "order_status"),
			 @Result(property = "flag",column = "flag"),
	 })
	 public ControlCenterDto selectById(@Param("orderSeq") BigInteger orderSeq);
	
}
