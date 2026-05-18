package com.tz.mapper;

import com.tz.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotMapper {
    List<Product> selectSales(String city);
    List<Product> selectRecommendationByCity(String city);

}

