package com.yuqi.shard.dao.impl;

import com.yuqi.shard.dao.mapper.OrderMapper;
import com.yuqi.shard.model.OrderPO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OrderDAO{

    @Resource
    private OrderMapper orderMapper;

    public List<OrderPO> getOrderListByUserId(Long userId) {
//        return jdbcTemplate.query("select order_id, user_id from t_order where user_id=? ", new Object[]{userId},
//                new int[]{Types.INTEGER}, new BeanPropertyRowMapper<>(
//                        OrderPO.class));
        return orderMapper.getOrderListByUserId(userId);

    }

//    @TargetDataSource("shardingDataSource")
    public void createOrder(OrderPO orderPO) {
//        String sb = "insert into t_order(user_id, order_id)" +
//                "values(" +
//                orderPO.getUserId() + "," +
//                orderPO.getOrderId() +
//                ")";
//        jdbcTemplate.update(sb);
        orderMapper.createOrder(orderPO);
        System.out.println(orderMapper.getOrderListByUserId(orderPO.getUserId()).toString());
    }
}
