package com.tz.order.controller;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/order")
    public LivegoodsResult showOrderList(String user){
        return orderService.showOrderList(user);
    }
}

