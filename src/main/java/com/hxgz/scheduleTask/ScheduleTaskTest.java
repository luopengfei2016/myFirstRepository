package com.hxgz.scheduleTask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jackson
 * @description 任务调度测试
 * @date 2019/6/10
 */
@Component
@EnableScheduling
public class ScheduleTaskTest {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    @Scheduled(cron = "* 10/1 12 * * *")
    public void reportCurrentTime() {
        System.out.println("现在的时间是：" + dateFormat.format(new Date()));
    }
}
