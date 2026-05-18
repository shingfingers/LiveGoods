package com.tz.banner.service.impl;

import com.tz.banner.mapper.BannerMapper;
import com.tz.banner.service.BannerService;
import com.tz.commons.redis.dao.RedisDao;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.pojo.Banner;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//发布所有当前类实现的接口
@Service
@DubboService
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Value("${livegoods.nginx.host}")
    private String nginxHost;
    @Autowired
    private RedisDao redisDao;

    @Value("${livegoods.redis.banner}")
    private String bannerKey;
    @Override
    public LivegoodsResult showBanner() {
        //边路缓存思想：先插redis，无再查mysql
        if (redisDao.hasKey(bannerKey)){
            return  redisDao.get(bannerKey);
        }
        //从数据库查出来的所有轮播图信息
        List<Banner> list = bannerMapper.selectShowBanner();
        //所有图片信息
        List<String> imgs=new ArrayList<>();
       list.forEach(banner -> imgs.add(nginxHost+banner.getUrl()));
       LivegoodsResult lg=LivegoodsResult.ok(imgs);
       redisDao.set(bannerKey,lg,7, TimeUnit.DAYS);
        return lg;
    }
}
