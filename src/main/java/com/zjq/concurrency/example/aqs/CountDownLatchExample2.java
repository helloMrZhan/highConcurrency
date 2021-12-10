package com.zjq.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>CountDownLatch</p>
 * wait()
 * @Author zjq
 * @Date 2021/12/10
 */
@Slf4j
public class CountDownLatchExample2 {
    /**
     * 线程数量
     */
    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    // 表示一个请求已经完成
                    countDownLatch.countDown();
                }
            });
        }
        //可以设置等待时间，超过这个时间就会执行，不会等到计数器变为0,但是之前给定的线程还是会执行完
        countDownLatch.await(20, TimeUnit.MILLISECONDS);
        //当这200个请求被处理完成之后，才会执行
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        // 模拟请求的耗时操作
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }
}
