package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.PayAccountDto;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:11 2019/9/25
 */
@Mapper
public interface PayAccountMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param payAccountDto
     */
    @Insert("INSERT INTO t_pay_account ( customer_id,  account_no,    bal,  freez_bal,  status) " +
            "VALUES( #{customerId},          #{accountNo},        #{bal},    #{freezBal},    #{status}) ")
    int insert(PayAccountDto payAccountDto);

    /**
     * 更新操作
     *
     * @param payAccountDto
     * @return 受影响的行数
     */
    @Update("UPDATE t_pay_account " +
            "SET account_no = #{accountNo},   bal = #{bal},  freez_bal = #{freezBal},  status = #{status} " +
            "WHERE customer_id = #{customerId} " )
    int update(PayAccountDto payAccountDto);

    /**
     * 删除操作
     *
     * @param customerId
     * @return 受影响的行数
     */
    @Delete(
            "DELETE " +
            "FROM " +
            "  t_pay_account " +
            "WHERE customer_id = #{customerId} " +
            " ")
    int delete(@Param("customerId") String customerId);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select customer_id,   account_no, bal, freez_bal, status from t_pay_account")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "accountNo",column = "account_no"),
            @Result(property = "bal",column = "bal"),
            @Result(property = "freezBal",column = "freez_bal"),
            @Result(property = "status",column = "status")
    })
    public List<PayAccountDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param customerId
     * @return
     */
    @Select("select customer_id,   account_no, bal, freez_bal, status from t_pay_account where  customer_id = #{customerId} ")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "accountNo",column = "account_no"),
            @Result(property = "bal",column = "bal"),
            @Result(property = "freezBal",column = "freez_bal"),
            @Result(property = "status",column = "status")
    })
    PayAccountDto selectById(@Param("customerId") String customerId);

}