package com.tz.details.mapper;

import com.tz.pojo.House;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
@MapperScan
public interface DetailsMapper {
    /**
     * 根据主键查询房屋信息
     * @param id
     * @return
     */
    House selectById(Long id);
}
