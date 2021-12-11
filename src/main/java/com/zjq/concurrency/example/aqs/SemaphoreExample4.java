package com.zjq.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p>Semaphore</p>
 * 未获取到许可，设置等待时长
 * @Author zjq
 * @Date 2021/12/10
 */
@Slf4j
public class SemaphoreExample4 {
    /**
     * 线程数量
     */
    private final static int threadCount = 15;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();
        //信号量设置为3，也就是最大并发量为3，同时只允许3个线程获得许可
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    //设置了获取许可等待时间为2秒，如果两秒后还是未获得许可的线程便得不到执行
                    if(semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        //释放许可
                        semaphore.release();
                    }
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        // 模拟请求的耗时操作
        Thread.sleep(1000);
        log.info("{}", threadNum);
    }
}
