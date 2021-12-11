package com.zjq.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>Semaphore</p>
 *
 * @Author zjq
 * @Date 2021/12/10
 */
@Slf4j
public class SemaphoreExample2 {
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
                    //获取多个许可
                    semaphore.acquire(3);
                    test(threadNum);
                    //释放多个许可
                    semaphore.release(3);
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
