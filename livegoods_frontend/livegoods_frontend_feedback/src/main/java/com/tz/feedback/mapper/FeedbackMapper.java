package com.tz.feedback.mapper;

import com.tz.pojo.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论表访问
 */
@Mapper
public interface FeedbackMapper {
    /**
     * 分页查询房屋的评论信息
     * @param houseId 房屋主键
     * @param pageStart 起始行
     * @param pageSize 每页大小
     * @return 查询结果
     */
    List<Feedback> selectByHouseId(Long houseId, int pageStart, int pageSize);


    /**
     * 查询房屋评论总数量
     * @param houseId 房屋主键
     * @return 数量
     */
    int selectCount(Long houseId);

    /**
     * 新增评论
     * @param feedback
     * @return
     */
    int insertFeedback(Feedback feedback);

}

