package com.tz.banner.controller;

import com.tz.banner.service.BannerService;
import com.tz.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;


    @GetMapping("/banner")
    public LivegoodsResult banner(){
        return bannerService.showBanner();
    }
}

