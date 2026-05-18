package com.tz.login.mapper;

import com.tz.pojo.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    /**
     * 新增登录日志
     * @param loginLog
     * @return
     */
    int insertLoginLog(LoginLog loginLog);
}

