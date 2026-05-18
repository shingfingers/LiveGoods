package com.tz.details.service.impl;

import com.tz.commons.redis.dao.RedisDao;
import com.tz.commons.utils.JSONUtils;
import com.tz.commons.vo.DetailsInfoVo;
import com.tz.commons.vo.DetailsVo;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.details.mapper.DetailsMapper;
import com.tz.details.service.DetailsService;
import com.tz.pojo.House;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@DubboService
@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private DetailsMapper detailsMapper;
    // 新增 - nginx服务器地址
    @Value("${livegoods.nginx.host}")
    private String nginxHost;
    // 新增 - 引入 详情 缓存key
    @Value("${livegoods.redis.details}")
    private String detailsKey;
    // 新增 - redis dao操作
    @Autowired
    private RedisDao redisDao;
    @Override
    public LivegoodsResult showHouse(Long id) {
        // 新增 - 判断是否已经缓存
        String key = detailsKey + ":"+ id;
        if(redisDao.hasKey(key)){
            return redisDao.get(key);
        }
        House house = detailsMapper.selectById(id);
        // 转换 ，把house对象转换为DetailsVo对象
        DetailsVo detailsVo = new DetailsVo();
        detailsVo.setId(house.getId());
        detailsVo.setBuy(house.getBuy());
        detailsVo.setCity(house.getCity());
        detailsVo.setHouseType(house.getHouseType());
        detailsVo.setRentType(house.getRentType());
        detailsVo.setTitle(house.getTitle());
        detailsVo.setPrice(house.getPrice());
        detailsVo.setBuyTime(house.getBuyTime());
        String imgs = house.getImgs();
        String[] imgsArray = imgs.split(",");
        detailsVo.setImgs(imgsArray);
        String info = house.getInfo();
        detailsVo.setInfo((DetailsInfoVo) JSONUtils.json2object(info, DetailsInfoVo.class));
        // 新增 - 查询到的数据添加到redis中，设置有效期为5天
        LivegoodsResult lg = LivegoodsResult.ok(detailsVo);
        redisDao.set(key,lg,7, TimeUnit.DAYS);
        return lg;
    }
    @Override
    public LivegoodsResult clearHouseCache(Long id) {
        String key = detailsKey + ":"+ id;
        redisDao.del(key);
        return LivegoodsResult.ok();
    }

}

