package com.tz.banner.mapper;

import com.tz.pojo.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    /**
     * 排序查询所有可见Banner
     * @return
     */
    List<Banner> selectShowBanner();
}

