package com.tz.details.service;

import com.tz.commons.vo.LivegoodsResult;

public interface DetailsService {
    /**
     * 显示房屋详情
     * @param id
     * @return
     */
    LivegoodsResult showHouse(Long id);
    /**
     * 清除房屋缓存
     * @param id
     * @return
     */
    LivegoodsResult clearHouseCache(Long id);

}

