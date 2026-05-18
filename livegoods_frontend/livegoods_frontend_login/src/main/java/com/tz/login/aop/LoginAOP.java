package com.tz.login.aop;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.login.mapper.LoginMapper;
import com.tz.pojo.LoginLog;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect // 当前类是一个切面类，在里面写增加方法。
@Component
public class LoginAOP {
    @Autowired
    private LoginMapper loginMapper;
    /*
    当前方式是service中login方法的后置通知方法。
     */
    @AfterReturning(value = "execution(* com.tz.login.service.impl.LoginServiceImpl.login(String,String)) && args(username,password)", returning = "lg")
    public void loginResult(String username, String password, LivegoodsResult lg){
        System.out.println("执行了后置通知"+username+","+password+","+lg);
        LoginLog loginLog = new LoginLog();;
        if(lg.getStatus()==200){
            loginLog.setLoginResult("成功");
        }else{
            loginLog.setLoginResult("失败");
        }
        loginLog.setPhone(username);
        loginLog.setLoginType("验证码");
        loginMapper.insertLoginLog(loginLog);
    }
}

