package com.tz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tz.banner.mapper")
public class FrontendBannerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendBannerApplication.class,args);
    }
}
