package com.tz.search.controller;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;
    @GetMapping("/search")
    public LivegoodsResult search(String city, String content, Integer page, @RequestParam(defaultValue = "3") Integer size){
        return searchService.search(city, content, page, size);
    }
}

