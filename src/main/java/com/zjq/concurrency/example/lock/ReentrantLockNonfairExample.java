package com.zjq.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock非公平锁实现
 *
 * @author zjq
 */
@Slf4j
public class ReentrantLockNonfairExample {

    /**
     * 定义一个非公平锁，new一个ReentrantLock的时候参数默认为false，可以不用指定为false
     */
    final static ReentrantLock lock = new ReentrantLock();

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
