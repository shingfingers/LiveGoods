package com.tz.order.service;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.pojo.Order;

public interface OrderService {
    LivegoodsResult insertOrder(Order order);
    /**
     * 显示订单信息
     * @param phone
     * @return
     */
    LivegoodsResult showOrderList(String phone);
    /**
     * 显示主键值对应的订单信息
     * @param id
     * @return
     */
    LivegoodsResult showOrderById(Long id);

    /**
     * 修改订单评论状态
     * @param id
     * @param state
     * @return
     */
    LivegoodsResult updateOrderState(Long id,Integer state);

}
