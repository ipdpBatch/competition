package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.PaymentInfoDto;
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
public interface PaymentInfoMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param paymentInfoDto
     */
    @Insert("INSERT INTO t_payment_info ( order_seq,  pay_type,  customer_id,  account_no_out,  account_no_in,  pay_amount,  pay_system,  return_code,  error_message) " +
            "VALUES( #{orderSeq},    #{payType},    #{customerId},    #{accountNoOut},    #{accountNoIn},    #{payAmount},    #{paySystem},    #{returnCode},    #{errorMessage}) ")
    int insert(PaymentInfoDto paymentInfoDto);

    /**
     * 更新操作
     *
     * @param paymentInfoDto
     * @return 受影响的行数
     */
    @Update("UPDATE t_payment_info " +
            "SET  customer_id = #{customerId},  account_no_out = #{accountNoOut},  account_no_in = #{accountNoIn},  pay_amount = #{payAmount},  pay_system = #{paySystem},  return_code = #{returnCode},  error_message = #{errorMessage} " +
            "WHERE order_seq = #{orderSeq} " +
            "  AND pay_type = #{payType} " )
    int update(PaymentInfoDto paymentInfoDto);

    /**
     * 删除操作
     *
     * @param id
     * @return 受影响的行数
     */
    @Delete(
            "DELETE " +
            "FROM " +
            "  t_payment_info " +
            "WHERE order_seq = #{orderSeq} " +
            "  AND pay_type = #{payType} " +
            " ")
    Long delete(@Param("orderSeq") BigInteger orderSeq, @Param("payType")String payType);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_payment_info")
    List<PaymentInfoDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select * from t_payment_info where  order_seq = #{orderSeq}  AND pay_type = #{payType}" )
    PaymentInfoDto selectById(@Param("orderSeq")BigInteger  orderSeq,@Param("payType")String payType);
}