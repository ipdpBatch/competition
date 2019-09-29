package com.octopus.common.dao.mapper;

import com.octopus.common.dao.PositionBalanceDto;
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
public interface PositionBalanceMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param positionBalanceDto
     */
    @Insert("INSERT INTO t_position_balance ( customer_id, product_id, total_volume, on_the_way_vol, on_the_way_amt ,position_status) VALUES (#{customerId},#{productId},#{totalVolume},#{onTheWayVol},#{onTheWayAmt},#{positionStatus})")
    int insert(PositionBalanceDto positionBalanceDto);

    /**
     * 更新操作
     *
     * @param positionBalanceDto
     * @return 受影响的行数
     */
    @Update("update t_position_balance set customer_id=#{customerId},product_id=#{productId},sign_status=#{signStatus},transcation_date=#{transcationDate},transcation_time=#{transcationTime},position_status=#{positionStatus}  where customer_id= #{customerId} and product_id= #{productId}")
    int update(PositionBalanceDto positionBalanceDto);

    /**
     * 删除操作
     *
     * @param customerId,productId
     * @return 受影响的行数
     */
    @Delete("delete from t_position_balance where customer_id=#{customerId} and product_id=#{productId}")
    Long delete(@Param("customerId") String customerId, @Param("productId") String productId);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_position_balance")
    List<PositionBalanceDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param customerId,productId
     * @return
     */
    @Select("select * from t_position_balance where customer_id=#{customerId} and product_id=#{productId}")
    PositionBalanceDto selectById(@Param("customerId") String customerId, @Param("productId") String productId);
}