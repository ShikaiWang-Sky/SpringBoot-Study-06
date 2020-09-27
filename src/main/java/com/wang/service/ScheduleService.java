package com.wang.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    //在一个特定的时间执行这个方法
    //cron表达式   sec min hour day month week     每天的10:43:00执行一次
    @Scheduled(cron = "0 43 10 * * ?")
    public void hello() {
        System.out.println("hello, 你被执行了!");
    }
}
