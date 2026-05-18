package com.tz.login.service.impl;

import com.tz.commons.redis.dao.RedisDao;
import com.tz.commons.vo.LivegoodsResult;
import com.tz.login.service.LoginService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@DubboService
@Service
public class LoginServiceImpl implements LoginService {
    @Value("${livegoods.redis.yzm}")
    private String yzmKey;
    @Autowired
    private RedisDao redisDao;
    @Override
    public LivegoodsResult sendYZM(String phone) {
        String key = yzmKey+phone;
        // 验证码在有效期范围内不允许重新发送
        if(redisDao.hasKey(key)){
            return LivegoodsResult.error("验证码有效期2分钟，请不要重复发送");
        }
        // 模拟四位验证码
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i =0;i<4;i++){
            int rand = random.nextInt(10);// 生成0~9的随机整数
            stringBuilder.append(rand);
        }
        // 生成的验证码
        String validateCode = stringBuilder.toString();
        System.out.println("验证码："+validateCode);
        // 验证码有效时间为2分钟
        redisDao.set(key,validateCode,2, TimeUnit.MINUTES);
        return LivegoodsResult.ok();
    }

    @Override
    public LivegoodsResult login(String username, String password) {
    /*
    校验：登录时手机号及验证码是否和redis中的验证码一致。
    如果一致登录成功。删除验证码。
    如果登录失败，允许重新输入
     */
        String key = yzmKey+username;
        if(redisDao.hasKey(key)){
            String validateCode = redisDao.get(key).toString();
            if(validateCode.equals(password)){// 登录成功
                redisDao.del(key);
                return LivegoodsResult.ok();
            }
        }
        return LivegoodsResult.error("用户名或密码错误，请重新输入");
    }


}

