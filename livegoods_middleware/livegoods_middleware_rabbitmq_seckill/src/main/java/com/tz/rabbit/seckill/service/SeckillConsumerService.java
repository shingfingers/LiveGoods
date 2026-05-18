package com.tz.rabbit.seckill.service;

import java.util.Map;

public interface SeckillConsumerService {
    Boolean seckill(Long houseId);
    void order(Map<String,Object> param);

}

