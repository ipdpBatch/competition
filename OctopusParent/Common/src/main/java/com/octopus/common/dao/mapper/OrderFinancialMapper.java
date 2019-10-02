package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.OrderFinancialDto;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019/9/26 11:44 PM
 * @Version 1.0
 */
@Mapper
public interface OrderFinancialMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param orderFinancialDto
     */
    @Options(useGeneratedKeys=true, keyProperty="orderSeq", keyColumn="order_seq")
    @Insert("INSERT INTO t_order_financial (create_date, create_time, transaction_code, customer_id, product_id, transaction_amount, transaction_vol, order_status, capital_status) \n" +
            "\tVALUES (#{createDate},#{createTime},#{transactionCode},#{customerId},#{productId},#{transactionAmount},#{transactionVol},#{orderStatus},#{capitalStatus})")
    public int insert(OrderFinancialDto orderFinancialDto);

    /**
     * 更新操作
     *
     * @param orderFinancialDto
     * @return 受影响的行数
     */
    @Update("update t_order_financial set create_date=#{createDate},create_time=#{createTime},transaction_code=#{transactionCode},customer_id=#{customerId},product_id=#{productId},transaction_amount=#{transactionAmount},transaction_vol=#{transactionVol},order_status=#{orderStatus},capital_status=#{capitalStatus} where order_seq= #{orderSeq}")
    public int update(OrderFinancialDto orderFinancialDto);

    /**
     * 删除操作
     *
     * @param orderSeq
     * @return 受影响的行数
     */
    @Delete("delete from t_order_financial where order_seq= #{orderSeq}")
    public int delete(@Param("orderSeq") BigInteger orderSeq);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select order_seq, create_date, create_time, transaction_code, customer_id, product_id, transaction_amount, transaction_vol, order_status, capital_status from t_order_financial")
    @Results({
            @Result(property = "orderSeq",column = "order_seq"),
            @Result(property = "createDate",column = "create_date"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "transactionCode",column = "transaction_code"),
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "productId",column = "product_id"),
            @Result(property = "transactionAmount",column = "transaction_amount"),
            @Result(property = "transactionVol",column = "transaction_vol"),
            @Result(property = "orderStatus",column = "order_status"),
            @Result(property = "capitalStatus",column = "capital_status")
    })
    public List<OrderFinancialDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param orderSeq
     * @return
     */
    @Select("select order_seq, create_date, create_time, transaction_code, customer_id, product_id, transaction_amount, transaction_vol, order_status, capital_status from t_order_financial where order_seq=#{orderSeq}")
    @Results({
            @Result(property = "orderSeq",column = "order_seq"),
            @Result(property = "createDate",column = "create_date"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "transactionCode",column = "transaction_code"),
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "productId",column = "product_id"),
            @Result(property = "transactionAmount",column = "transaction_amount"),
            @Result(property = "transactionVol",column = "transaction_vol"),
            @Result(property = "orderStatus",column = "order_status"),
            @Result(property = "capitalStatus",column = "capital_status")
    })
    public OrderFinancialDto selectById(@Param("orderSeq") BigInteger orderSeq);

}
