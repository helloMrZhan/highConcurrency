package com.zjq.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//         //延迟3秒后执行
//        }, 3, TimeUnit.SECONDS);
        //        executorService.shutdown();

//        executorService.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("scheduleWithFixedDelay run");
//            }
//            //延迟一秒后每隔3秒执行
//        }, 1, 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("scheduleAtFixedRate run");
            }
            //延迟一秒后每隔3秒执行
        }, 1, 3, TimeUnit.SECONDS);

        /**
         * 定时器调度，不推荐使用，推荐ScheduledExecutorService调度
         */
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.warn("timer run");
//            }
//        //从当前时间每隔5秒执行
//        }, new Date(), 5 * 1000);
    }
}
