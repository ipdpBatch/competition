package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.OrderFinancialDto;
import org.apache.ibatis.annotations.*;

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
    @Insert("INSERT INTO t_order_financial (order_seq, create_date, create_time, transcation_code, customer_id, product_id, transcation_amout, transcation_vol, order_status, capital_status) \n" +
            "\tVALUES (#{orderSeq},#{createDate},#{createTime},#{transcationCode},#{customerId},#{productId},#{transcationAmout},#{transcationVol},#{orderStatus},#{capitalStatus})")
    public int insert(OrderFinancialDto orderFinancialDto);

    /**
     * 更新操作
     *
     * @param orderFinancialDto
     * @return 受影响的行数
     */
    @Update("update t_control_order set create_date=#{createDate},create_time=#{createTime},transcation_code=#{transcationCode},customer_id=#{customerId},product_id=#{productId},transcation_amout=#{transcationAmout},transcation_vol=#{transcationVol},order_status=#{orderStatus},capital_status=#{capitalStatus} where order_seq= #{orderSeq}")
    public int update(OrderFinancialDto orderFinancialDto);

    /**
     * 删除操作
     *
     * @param orderSeq
     * @return 受影响的行数
     */
    @Delete("delete from t_order_financial where order_seq= #{orderSeq}")
    public int delete(@Param("orderSeq") float orderSeq);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select order_seq, create_date, create_time, transcation_code, customer_id, product_id, transcation_amout, transcation_vol, order_status, capital_status from t_order_financial")
    public List<OrderFinancialDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param orderSeq
     * @return
     */
    @Select("select order_seq, create_date, create_time, transcation_code, customer_id, product_id, transcation_amout, transcation_vol, order_status, capital_status from t_order_financial where order_seq=#{orderSeq}")
    public OrderFinancialDto selectById(@Param("orderSeq") float orderSeq);

}
