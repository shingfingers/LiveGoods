package com.tz.commons.redis.dao.impl;

import com.tz.commons.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * 通用Redis数据访问实现。
 */
@Repository
public class RedisDaoImpl implements RedisDao {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }


    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }


    @Override
    public void set(String key, Object value, long times, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, times, unit);
    }


    @Override
    public void setnx(String key, Object value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }


    @Override
    public void setnx(String key, Object value, long times, TimeUnit unit) {
        redisTemplate.opsForValue().setIfAbsent(key, value, times, unit);
    }


    @Override
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }


    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }


    @Override
    public void expire(String key, long times, TimeUnit unit) {
        redisTemplate.expire(key, times, unit);
    }


    @Override
    public void persist(String key) {
        redisTemplate.persist(key);
    }


    @Override
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }


    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


}

