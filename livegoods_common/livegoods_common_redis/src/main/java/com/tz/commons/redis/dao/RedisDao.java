package com.tz.commons.redis.dao;


import java.util.concurrent.TimeUnit;

public interface RedisDao {
    /**
     * 自增方法。
     * @param key
     * @return
     */
    Long incr(String key);


    /**
     * 保存键值对到redis
     * @param key
     * @param value
     */
    void set(String key, Object value);


    /**
     * 保存键值对到redis，并设置有效期
     * @param key
     * @param value
     * @param times
     * @param unit
     */
    void set(String key, Object value, long times, TimeUnit unit);


    /**
     * 强制新增键值对到redis
     * @param key
     * @param value
     */
    void setnx(String key, Object value);


    /**
     * 强制新增键值对到redis，并设置有效期
     * @param key
     * @param value
     * @param times
     * @param unit
     */
    void setnx(String key, Object value, long times, TimeUnit unit);


    /**
     * 根据key查询value
     * @param key
     * @param <T>
     * @return
     */
    <T> T get(String key);


    /**
     * 删除键值对
     * @param key
     */
    void del(String key);


    /**
     * 设置键值对有效期。
     * @param key
     * @param times
     * @param unit
     */
    void expire(String key, long times, TimeUnit unit);


    /**
     * 删除键值对有效期
     * @param key
     */
    void persist(String key);


    /**
     * 查询键值对剩余有效时长，单位是秒
     * @param key
     * @return
     */
    long ttl(String key);


    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    Boolean hasKey(String key);
}

