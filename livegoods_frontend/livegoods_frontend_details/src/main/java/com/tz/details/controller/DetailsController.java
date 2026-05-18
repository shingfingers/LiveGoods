package com.tz.details.controller;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.details.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsController {
    @Autowired
    private DetailsService detailsService;


    @GetMapping("/details")
    public LivegoodsResult showDetails(Long id){
        return detailsService.showHouse(id);
    }
}

