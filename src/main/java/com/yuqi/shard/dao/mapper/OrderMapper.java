package com.yuqi.shard.dao.mapper;

import com.yuqi.shard.aspect.ShardMapper;
import com.yuqi.shard.aspect.TargetDataSource;
import com.yuqi.shard.model.OrderPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@ShardMapper
public interface OrderMapper {

    @TargetDataSource("shardingDataSource")
    List<OrderPO> getOrderListByUserId(@Param("userId")Long userId);

    @TargetDataSource("shardingDataSource")
    void createOrder(OrderPO orderPO);

}
