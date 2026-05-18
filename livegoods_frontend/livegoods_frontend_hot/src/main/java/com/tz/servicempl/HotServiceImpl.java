package com.tz.servicempl;

import com.tz.HotService;
import com.tz.commons.redis.dao.RedisDao;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.mapper.HotMapper;
import com.tz.pojo.Product;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
@DubboService
public class HotServiceImpl implements HotService {
    @Autowired
    private HotMapper hotMapper;
    @Value("${livegoods.nginx.host}")
    private String nginxHost;
    @Value("${livegoods.redis.hot}")
    private String hotKey;
    @Value("${livegoods.redis.recommendation}")
    private String recommendation;
    @Autowired
    private RedisDao redisDao;
    @Override
    public LivegoodsResult showHotsales(String city) {
        if (redisDao.hasKey(hotKey)){
            return redisDao.get(hotKey);
        }

        List<Product> list = hotMapper.selectSales(city);
        list.forEach(product -> {
            product.setImg(nginxHost+product.getImg());
        });
        LivegoodsResult lg = LivegoodsResult.ok(list);
        redisDao.set(hotKey,lg,7, TimeUnit.DAYS);
        return lg;
    }

    @Override
    public LivegoodsResult showRecommendation(String city) {
        if (redisDao.hasKey(recommendation)){
            return redisDao.get(recommendation);
        }
        List<Product> list = hotMapper.selectRecommendationByCity(city);
        list.forEach(product -> {
            product.setImg(nginxHost+product.getImg());
        });
        LivegoodsResult lg = LivegoodsResult.ok(list);
        redisDao.set(recommendation,lg,7, TimeUnit.DAYS);
        return lg;
    }

}

