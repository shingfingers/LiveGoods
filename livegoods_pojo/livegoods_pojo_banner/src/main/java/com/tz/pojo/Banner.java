package com.tz.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Banner implements Serializable {
    // 主键
    private Long id;
    // 不包含Nignx的图片地址
    private String url;
    // 显示排序
    private Integer sort;
    // 是否显示
    private Boolean visible;
}

