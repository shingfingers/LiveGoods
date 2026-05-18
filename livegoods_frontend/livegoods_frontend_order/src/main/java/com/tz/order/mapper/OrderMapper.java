package com.tz.order.mapper;  // 去掉多余的 .com

import com.tz.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    List<Order> selectOrderByPhone(String phone);
    /**
     * 根据主键查询订单信息
     * @param id
     * @return
     */
    Order selectOrderById(Long id);
    /**
     * 修改订单评论状态
     * @param id
     * @param state
     * @return
     */
    int updateStateById(Long id,Integer state);

}