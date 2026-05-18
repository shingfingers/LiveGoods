package com.tz.commons.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专门用在显示房屋详情信息的视图对象
 */
@Data
public class DetailsVo implements Serializable{
    // 主键
    private Long id;
    // 标题
    private String title;
    // 租赁类型
    private String rentType;
    // 价格
    private Long price;
    // 房屋类型
    private String houseType;
    // 房屋信息
    private DetailsInfoVo info;
    // 所有的图片地址
    private String[] imgs;
    // 所属城市
    private String city;
    // 可预定时间
    private Date buyTime;
    // 是否已经被预定
    private Boolean buy;
}

