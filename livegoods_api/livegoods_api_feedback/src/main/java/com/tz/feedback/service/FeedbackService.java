package com.tz.feedback.service;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.order.service.OrderService;
import com.tz.pojo.Feedback;
import com.tz.pojo.Order;
import org.apache.dubbo.config.annotation.DubboReference;

public interface FeedbackService {
    /**
     * 显示评论
     * @param id 主键
     * @param page 页码
     * @param pageSize 每页大小
     * @return
     */
    LivegoodsResult showFeedback(Long id, Integer page, Integer pageSize);


    /**
     * 添加评论
     */
    LivegoodsResult addComment(Long orderId, String feedback, Integer star);

}

