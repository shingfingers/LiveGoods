package com.tz.pojo;

import lombok.Data;

/**
 * 评论实体类
 */
@Data
public class Feedback {
    // 主键
    private Long id;
    // 评论内容
    private String comment;
    // 评星
    private Integer star;
    // 评论用户名
    private String username;
    // 评论哪个房屋
    private Long houseId;
    // 所属订单
    private Long orderId;
}

