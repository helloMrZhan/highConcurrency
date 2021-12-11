package com.zjq.concurrency.example.lock;

import com.zjq.concurrency.anno.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock公平锁实现
 *
 * @author zjq
 */
@Slf4j
public class ReentrantLockFairExample {

    /**
     * 定义一个公平锁
     */
    final static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws Exception {
        new Thread(() -> testLock(), "线程壹号").start();
        new Thread(() -> testLock(), "线程贰号").start();
        new Thread(() -> testLock(), "线程叁号").start();
    }

    private static void testLock() {
        for (int i = 0; i < 2; i++) {
            //操作前加锁
            lock.lock();
            try {
                log.info("{}获取了锁", Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //操作后在finally中关闭锁，确保锁成功释放，避免死锁
                lock.unlock();
            }
        }
    }
}
