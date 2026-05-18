package com.tz.seckill.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface SeckillMapper {
    Date selectBuytimeById(Long id);
}

