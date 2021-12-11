package com.zjq.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 响应中断
 *
 * @author zjq
 */
@Slf4j
public class ReentrantLockInterruptblyExample {

    /**
     * 定义一个非公平锁，new一个ReentrantLock的时候参数默认为false，可以不用指定为false
     */
    final static ReentrantLock lock1 = new ReentrantLock();
    final static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(new ThreadTest(lock1, lock2), "线程壹号");
        Thread thread2 = new Thread(new ThreadTest(lock2, lock1), "线程贰号");
        thread1.start();
        thread2.start();
        thread1.interrupt();
    }


    static class ThreadTest implements Runnable {

        Lock lock111;
        Lock lock222;

        public ThreadTest(Lock lock111, Lock lock222) {
            this.lock111 = lock111;
            this.lock222 = lock222;
        }

        @Override
        public void run() {
            try {
                //如果当前线程未被中断，则获取锁定；如果已中断，则抛出异常
                lock111.lockInterruptibly();
                TimeUnit.MILLISECONDS.sleep(50);
                lock222.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock111.unlock();
                lock222.unlock();
                log.info("{}获取到了锁", Thread.currentThread().getName());
            }

        }
    }
}
