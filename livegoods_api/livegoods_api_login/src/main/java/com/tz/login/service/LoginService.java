package com.tz.login.service;

import com.tz.commons.vo.LivegoodsResult;

/**
 * 登录服务接口
 */
public interface LoginService {
    /**
     * 发送验证码业务
     * @param phone 手机号
     * @return 操作结果
     */
    LivegoodsResult sendYZM(String phone);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    LivegoodsResult login(String username,String password);

}

