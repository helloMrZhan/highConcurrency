package com.zjq.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * FutureTask
 * @author zjq
 */
@Slf4j
public class FutureTaskExample2 {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("callable耗时任务开始");
                //耗时任务
                Thread.sleep(5000);
                log.info("callable耗时任务完成");
                return "耗时任务：报告！我已完成";
            }
        });
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureTask);
        //executor.execute(futureTask);
        log.info("主线程任务开始");
        Thread.sleep(1000);
        log.info("主线程任务完成");
        log.info("等待耗时任务完成。。。");
        //获取耗时任务的返回结果，如果未返回，主线程将阻塞，处于等待状态
        String result = futureTask.get();
        log.info("result：{}", result);
    }
}
