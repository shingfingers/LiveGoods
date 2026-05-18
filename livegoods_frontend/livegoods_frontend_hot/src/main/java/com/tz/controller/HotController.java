package com.tz.controller;

import com.tz.HotService;
import com.tz.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotController {
    @Autowired
    private HotService hotService;


    @GetMapping("/hotProduct")
    public LivegoodsResult hotProduct(String city){
        return hotService.showHotsales(city);
    }
    @GetMapping("/recommendation")
    public LivegoodsResult recommendation(String city){
        return hotService.showRecommendation(city);
    }

}

