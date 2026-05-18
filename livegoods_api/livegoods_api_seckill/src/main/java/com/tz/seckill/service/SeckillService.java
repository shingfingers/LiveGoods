package com.tz.seckill.service;

import com.tz.commons.vo.LivegoodsResult;

public interface SeckillService {
    LivegoodsResult buytime(Long id);
    LivegoodsResult buyaction(Long houseId,String phone);

}
