package com.tz.feedback.service.impl;

import com.tz.commons.vo.HasMoreResult;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.feedback.mapper.FeedbackMapper;
import com.tz.feedback.service.FeedbackService;
import com.tz.order.service.OrderService;
import com.tz.pojo.Feedback;
import com.tz.pojo.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DubboService
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Override
    public LivegoodsResult showFeedback(Long id, Integer page, Integer pageSize) {
        HasMoreResult hasMoreResult = new HasMoreResult();
        List<Feedback> list = feedbackMapper.selectByHouseId(id, page*pageSize, pageSize);
        //数据脱敏
        list.forEach(feedback -> {
            String username = feedback.getUsername()
                    .replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            feedback.setUsername(username);
        });
        hasMoreResult.setContents(list);
        int total = feedbackMapper.selectCount(id);
        int totalPage = total%pageSize==0?total/pageSize:total/pageSize+1;
        if(totalPage>page+1){
            hasMoreResult.setHasMore(true);
        }else{
            hasMoreResult.setHasMore(false);
        }
        return LivegoodsResult.ok(hasMoreResult);
    }


    @DubboReference
    private OrderService orderService;
    @GlobalTransactional
    @Transactional
    @Override
    public LivegoodsResult addComment(Long orderId, String feedback, Integer star) {
        Feedback fb = new Feedback();
        fb.setComment(feedback);
        fb.setOrderId(orderId);
        fb.setStar(star);
        LivegoodsResult lg = orderService.showOrderById(orderId);
        Order order = (Order) lg.getData();
        fb.setUsername(order.getUserPhone());
        fb.setHouseId(order.getHouseId());
        int index = feedbackMapper.insertFeedback(fb);
        if(index==1){
            return LivegoodsResult.ok();
        }
        return LivegoodsResult.error("评论失败");
    }



}

