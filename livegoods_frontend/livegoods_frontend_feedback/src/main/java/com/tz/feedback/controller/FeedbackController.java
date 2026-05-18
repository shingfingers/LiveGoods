package com.tz.feedback.controller;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @GetMapping("/comment")
    public LivegoodsResult showFeedback(Long id, Integer page
            , @RequestParam(defaultValue = "5") Integer pageSize){
        return feedbackService.showFeedback(id, page, pageSize);
    }
    @PostMapping("/feedback")
    public LivegoodsResult feedback(Long orderId,String feedback,Integer rate){
        return feedbackService.addComment(orderId, feedback, rate);
    }

}

