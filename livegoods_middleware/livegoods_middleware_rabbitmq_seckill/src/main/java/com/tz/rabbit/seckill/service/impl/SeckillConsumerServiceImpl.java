package com.tz.rabbit.seckill.service.impl;

import com.tz.commons.vo.DetailsVo;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.details.service.DetailsService;
import com.tz.order.service.OrderService;
import com.tz.pojo.Order;
import com.tz.rabbit.seckill.mapper.RabbitConsumerMapper;
import com.tz.rabbit.seckill.service.SeckillConsumerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


// Dubbo 服务类
    @DubboService
    @Service
    public class SeckillConsumerServiceImpl implements SeckillConsumerService {
        @DubboReference
        private DetailsService detailsService;
        @Autowired
        private RabbitConsumerMapper rabbitConsumerMapper;
        @RabbitListener(queues = "livegoods.queue.seckill")
        @Override
        public Boolean seckill(Long houseId) {
            LivegoodsResult livegoodsResult = detailsService.showHouse(houseId);
            // dubbo远程服务调用时，如果返回值里面还嵌套对象时。
            DetailsVo detailsVo= (DetailsVo) livegoodsResult.getData();
            if(detailsVo.getBuy()){// 已经被预定了
                return false;
            }
            Boolean isBuy = rabbitConsumerMapper.selectBuyById(houseId);
            if(isBuy){// 执行到这，说明redis缓存中buy=0
                // 同步缓存
                detailsService.clearHouseCache(houseId);
                detailsService.showHouse(houseId);
                return false;
            }
            // 执行到这里：说明缓存和数据库中buy=0
            int index = rabbitConsumerMapper.updateBuyById(houseId);
            if(index==1){
                // 数据库修改成功，需要同步缓存
                detailsService.clearHouseCache(houseId);
                detailsService.showHouse(houseId);
                return true;
            }
            return false;
        }

    @DubboReference
    private OrderService orderService;


    @RabbitListener(queues = "livegoods.queue.order")
    @Override
    public void order(Map<String, Object> param) {
        System.out.println("接收到的参数:"+param);
        Order order = new Order();
        order.setCommentState((byte)0);
        long id = Long.parseLong(param.get("id").toString());
        order.setHouseId(id);
        order.setUserPhone(param.get("phone").toString());
        System.out.println("id:"+id);
        LivegoodsResult livegoodsResult = detailsService.showHouse(id);
        DetailsVo detailsVo = (DetailsVo) livegoodsResult.getData();
        order.setImg(detailsVo.getImgs()[0]);
        order.setPrice(detailsVo.getPrice());
        order.setHouseTitle(detailsVo.getTitle());
        order.setHouseType(detailsVo.getHouseType());
        order.setRentType(detailsVo.getRentType());
        orderService.insertOrder(order);
    }


}



