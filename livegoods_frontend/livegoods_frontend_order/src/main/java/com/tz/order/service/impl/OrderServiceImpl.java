package com.tz.order.service.impl;

import com.tz.order.mapper.OrderMapper;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.order.service.OrderService;
import com.tz.pojo.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@DubboService
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public LivegoodsResult insertOrder(Order order) {
        long time = System.currentTimeMillis();
        Random random = new Random();
        String id = time + "" +random.nextInt(100);
        order.setId(Long.parseLong(id));
        int index = orderMapper.insertOrder(order);
        if(index==1){
            return LivegoodsResult.ok();
        }
        return LivegoodsResult.error("新增订单失败");
    }

    @Override
    public LivegoodsResult showOrderList(String phone) {
        List<Order> list = orderMapper.selectOrderByPhone(phone);
        return LivegoodsResult.ok(list);
    }

    @Override
    public LivegoodsResult showOrderById(Long id) {
        Order order = orderMapper.selectOrderById(id);
        return LivegoodsResult.ok(order);
    }
    @Transactional
    @GlobalTransactional
    @Override
    public LivegoodsResult updateOrderState(Long id, Integer state) {
        int index = orderMapper.updateStateById(id, state);
        if(index==1){
            return LivegoodsResult.ok();
        }
        return LivegoodsResult.error("修改订单评论状态失败");
    }


}

