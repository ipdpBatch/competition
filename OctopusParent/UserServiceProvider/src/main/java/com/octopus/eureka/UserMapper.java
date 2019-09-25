package com.octopus.eureka;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:11 2019/9/25
 */
@Mapper
public interface UserMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param userDao
     */
    @Insert("INSERT INTO t_customer_cif_info ( customer_id, customer_name, certification_id, certification_type, risk_level, is_signed ) VALUES (#{id},#{name},#{idno},#{idtype},#{risklv},#{issigned})")
    int insert(UserDao userDao);

    /**
     * 更新操作
     *
     * @param userDao
     * @return 受影响的行数
     */
    @Update("update t_customer_cif_info set customer_id=#{id},customer_name=#{name},certification_id=#{idno},certification_type=#{idtype},risk_level=#{risklv},is_signed={issigned}  where customer_id= #{id}")
    int update(UserDao userDao);

    /**
     * 删除操作
     *
     * @param id
     * @return 受影响的行数
     */
    @Delete("delete from t_customer_cif_info where customer_id=#{id}")
    Long delete(@Param("id") String id);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_customer_cif_info")
    List<UserDao> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select id,name,age from person where id=#{id}")
    UserDao selectById(@Param("id") Long id);
}