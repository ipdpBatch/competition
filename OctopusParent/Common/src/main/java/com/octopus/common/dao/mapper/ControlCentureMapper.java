package com.octopus.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.octopus.common.dao.domain.ControlCentureDto;

@Mapper
public interface ControlCentureMapper {
     /**
      * 插入
      * @param controlCentureDto
      * @return
      */
	 @Insert("INSERT INTO t_control_centure (order_seq,request_time, update_time,order_step, order_status, flag) VALUES (#{orderSeq},#{requestTime},#{updateTime},#{orderStep},#{orderStatus},#{flag})")
	 public int insert(ControlCentureDto controlCentureDto);
	 /**
	  * 更新
	  * @param controlCentureDto
	  * @return
	  */
	 @Update("update t_control_centure set request_time=#{requestTime},update_time=#{updateTime}, order_step=#{orderStep},order_status=#{orderStatus}, flag=#{flag} where order_seq= #{orderSeq}")
	 public int update(ControlCentureDto controlCentureDto);
	 /**
	  * 删除
	  * @param orderSeq
	  * @return
	  */
	 @Delete("delete from t_control_centure where order_seq= #{orderSeq}")
	 public int delete(@Param("orderSeq") String orderSeq);
	 
	 /**
	  * 查询所有
	  * @return
	  */
	 @Select("select * from t_control_centure")
	 public List<ControlCentureDto> selectAll();
	 
	 /**
	  * 查询单条
	  * @param orderSeq
	  * @return
	  */
	 @Select("select * from t_control_centure where order_seq=#{orderSeq}")
	 public ControlCentureDto selectById(@Param("orderSeq") String orderSeq);
	
}
