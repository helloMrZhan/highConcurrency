package com.zjq.concurrency.example.commonUnsafe;

import com.zjq.concurrency.anno.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 多线程同时操作全局字符串的时候使用StringBuffer能保证线程安全
 * @author zjq
 */
@Slf4j
@ThreadSafe
public class StringExample2 {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws Exception {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量（并发线程数）
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器  （把请求计数）
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    //信号量  判断进程是否执行
                    semaphore.acquire();
                    append();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                //计数器减1
                countDownLatch.countDown();
            });
        }
        //当所有请求结束
        countDownLatch.await();
        executorService.shutdown();
        log.info("最终字符串长度:{}", stringBuffer.length());
    }

    private static void append() {
        stringBuffer.append("1");
    }
}
