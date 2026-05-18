package com.tz.seckill.service.impl;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.seckill.mapper.SeckillMapper;
import com.tz.seckill.service.SeckillService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@DubboService
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private SeckillMapper seckillMapper;
    @Override
    public LivegoodsResult buytime(Long id) {
        Date date = seckillMapper.selectBuytimeById(id);
        if (date == null) {
            return LivegoodsResult.error("该房源未设置开售时间");
        }
        return LivegoodsResult.ok(date.getTime());
    }

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public LivegoodsResult buyaction(Long houseId, String phone) {
        // 同步消息：超时后会自动向下执行，不会一直阻塞。 返回null。
        Boolean result = (Boolean) amqpTemplate.convertSendAndReceive
                ("livegoods.exchange.seckill","livegoods.queue.seckill",houseId);
        if(result!=null&&result){
      /*
        预定成功后：
          1. 创建订单
          2. 发送短信
          3. 发送邮件
          4. 等多个事情。
        可以使用一个扇形交换器，发送异步消息。来完成上面的事情。
        不同事情，绑定不同的队列。这些队列都绑定到一个交换器上。
        当前项目只做了创建订单功能，已经能够体现出来结构了。
       */
            Map<String,Object> param = new HashMap<>();
            param.put("id",houseId);
            param.put("phone",phone);
            amqpTemplate.convertAndSend("livegoods.exchange.order","",param);
            return LivegoodsResult.ok();
        }
        return LivegoodsResult.error("预定失败");
    }

}

