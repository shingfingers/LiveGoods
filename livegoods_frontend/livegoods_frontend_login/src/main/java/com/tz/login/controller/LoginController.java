package com.tz.login.controller;

import com.tz.commons.vo.LivegoodsResult;
import com.tz.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


    @PostMapping("/sendyzm")
    public LivegoodsResult sendyzm(String phone){
        return loginService.sendYZM(phone);
    }

    @PostMapping("/login")
    public LivegoodsResult login(String username,String password){
        return loginService.login(username, password);
    }

}

