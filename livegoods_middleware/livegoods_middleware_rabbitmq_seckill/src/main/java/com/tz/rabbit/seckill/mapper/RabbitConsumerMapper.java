package com.tz.rabbit.seckill.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RabbitConsumerMapper {
    Boolean selectBuyById(Long id);
    int updateBuyById(Long id);
}

