package com.tz.seckill.controller;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeckillController {
    @Autowired
  private SeckillService seckillService;


    @GetMapping("/buytime")
    public LivegoodsResult seckill(Long id){
        return seckillService.buytime(id);
    }

    @GetMapping("/buyaction")
    public LivegoodsResult buyaction(Long id,String user){
        return seckillService.buyaction(id, user);
    }

}

