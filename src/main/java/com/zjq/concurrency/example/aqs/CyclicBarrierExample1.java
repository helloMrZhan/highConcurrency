package com.zjq.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zjq
 * @date 2021/12/11 16:06
 * <p>title: CyclicBarrier</p>
 * <p>description:</p>
 */
@Slf4j
public class CyclicBarrierExample1 {
    /**
     * 线程数量
     */
    private final static int threadCount = 15;
    /**
     * 屏障拦截的线程数量为5，表示每次屏障会拦截5个线程
     */
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready {}", threadNum,barrier.getNumberWaiting());
        //每次调用await方法后计数器+1，当前线程被阻塞
        barrier.await();
        log.info("{} continue", threadNum);
    }
}
