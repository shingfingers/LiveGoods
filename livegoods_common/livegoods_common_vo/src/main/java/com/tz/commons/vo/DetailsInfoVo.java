package com.tz.commons.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专门用在显示房屋详情信息的视图对象
 */
@Data
public class DetailsInfoVo implements Serializable{
    private String years;
    private String type;
    private String level;
    private String style;
    private String orientation;
}


