package com.tz.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 带有分页效果的结果中
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HasMoreResult implements Serializable {
    // 是否还有下一页数据
    private Boolean hasMore;
    // 当前页数据
    private Object contents;
}
