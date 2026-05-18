package com.tz.search.service;

import com.tz.commons.vo.LivegoodsResult;

public interface SearchService {
    LivegoodsResult search(String city, String content, Integer page, Integer size);
}

