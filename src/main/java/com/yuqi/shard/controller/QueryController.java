package com.yuqi.shard.controller;

import com.yuqi.shard.test.Scanner;
import com.yuqi.shard.aspect.ShardMapper;
import com.yuqi.shard.dao.impl.OrderDAO;
import com.yuqi.shard.model.OrderPO;
import com.yuqi.shard.model.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RestController
public class QueryController {

    @Resource
    private DataSource dataSource;

    @Resource
    private OrderDAO orderDAO;

    @PostMapping("/order/query")
    public void query(@RequestBody OrderVO orderVO) {
//        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
//        try (
//                Connection conn = dataSource.getConnection();
//                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            preparedStatement.setInt(1, 10);
//            preparedStatement.setInt(2, 1001);
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    System.out.println(rs.getInt(1));
//                    System.out.println(rs.getInt(2));
//                }
//            }
//        }
        System.out.println("+++++++++开始插入+++++++++");
        orderDAO.createOrder(transfer(orderVO));
//        List<OrderPO> list = orderDAO.getOrderListByUserId(orderVO.getUserId());
//        System.out.println("list的值为：" + list.toString());
    }

    @GetMapping("/scan")
    public void scan() {
        String BASE_PACKAGE = "com.yuqi.shard.dao";
        GenericApplicationContext context = new GenericApplicationContext();
        Scanner myClassPathDefinitonScanner = new Scanner(context, ShardMapper.class);
        // 注册过滤器
        myClassPathDefinitonScanner.registerTypeFilter();
        int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
        context.refresh();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanCount);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    private OrderPO transfer(OrderVO orderVO) {
        OrderPO orderPO = new OrderPO();
        BeanUtils.copyProperties(orderVO, orderPO);
        return orderPO;
    }

}
