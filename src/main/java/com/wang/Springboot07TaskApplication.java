package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启异步方法的注解
@EnableAsync
//开启定时任务功能的注解
@EnableScheduling
public class Springboot07TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot07TaskApplication.class, args);
    }
}
