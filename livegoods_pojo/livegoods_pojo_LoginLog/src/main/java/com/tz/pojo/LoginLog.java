package com.tz.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginLog {
    private Long id;
    private String phone;
    private String loginType;
    private String loginResult;
    private Date loginTime;
}
