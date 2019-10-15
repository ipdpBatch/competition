package com.octopus.common.dao.mapper;

import com.octopus.common.dao.domain.PositionBalanceDto;
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
    @Options(useGeneratedKeys=true, keyProperty="customer_id, product_id", keyColumn="customer_id, product_id")
    @Insert("INSERT INTO t_position_balance ( customer_id, product_id, total_volume, on_the_way_vol, on_the_way_amt ,position_status) VALUES (#{customerId},#{productId},#{totalVolume},#{onTheWayVol},#{onTheWayAmt},#{positionStatus})")
    public int insert(PositionBalanceDto positionBalanceDto);

    /**
     * 更新操作
     *
     * @param positionBalanceDto
     * @return 受影响的行数
     */
    @Update("update t_position_balance set customer_id=#{customerId},product_id=#{productId},total_volume=#{totalVolume},on_the_way_vol=#{onTheWayVol},on_the_way_amt=#{onTheWayAmt},position_status=#{positionStatus}  where customer_id= #{customerId} and product_id= #{productId}")
    public int update(PositionBalanceDto positionBalanceDto);

    /**
     * 删除操作
     *
     * @param productId,customerId
     * @return 受影响的行数
     */
    @Delete("delete from t_position_balance where customer_id=#{customerId} and product_id=#{productId}")
    public int delete(@Param("productId") String productId, @Param("customerId") String customerId);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select customer_id, product_id, total_volume, on_the_way_vol, on_the_way_amt, position_status from t_position_balance")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "productId",column = "product_id"),
            @Result(property = "totalVolume",column = "total_volume"),
            @Result(property = "onTheWayVol",column = "on_the_way_vol"),
            @Result(property = "onTheWayAmt",column = "on_the_way_amt"),
            @Result(property = "positionStatus",column = "position_status")
    })
    public List<PositionBalanceDto> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param productId,customerId
     * @return
     */
    @Select("select customer_id, product_id, total_volume, on_the_way_vol, on_the_way_amt, position_status from t_position_balance where customer_id=#{customerId} and product_id=#{productId}")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "productId",column = "product_id"),
            @Result(property = "totalVolume",column = "total_volume"),
            @Result(property = "onTheWayVol",column = "on_the_way_vol"),
            @Result(property = "onTheWayAmt",column = "on_the_way_amt"),
            @Result(property = "positionStatus",column = "position_status")
    })
    public PositionBalanceDto selectById(@Param("productId") String productId, @Param("customerId") String customerId);

    /**
     * 动态查询
     *
     * @param positionBalanceDto
     * @return
     */
    @Select("<script>"+
            "select customer_id, product_id, total_volume, on_the_way_vol, on_the_way_amt, position_status " +
            "from t_position_balance where 1=1 "+
            "<if test=\"productId!=null and productId != ''\">and product_id=#{productId} </if>"+
            "<if test=\"customerId!=null and customerId != ''\">and customer_id=#{customerId}</if>"+
            "</script>")
    @Results({
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "productId",column = "product_id"),
            @Result(property = "totalVolume",column = "total_volume"),
            @Result(property = "onTheWayVol",column = "on_the_way_vol"),
            @Result(property = "onTheWayAmt",column = "on_the_way_amt"),
            @Result(property = "positionStatus",column = "position_status")
    })
    public List<PositionBalanceDto> selectDynamic(PositionBalanceDto positionBalanceDto);
}