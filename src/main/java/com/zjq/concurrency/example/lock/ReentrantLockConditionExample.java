package com.zjq.concurrency.example.lock;

import com.zjq.concurrency.anno.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock Condition的使用
 * @author zjq
 */
@Slf4j
public class ReentrantLockConditionExample {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    public static int count = 0;
    /**
     * 定义一个可重入锁
     */
    final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
            //创建一个可重入锁
            ReentrantLock reentrantLock = new ReentrantLock();
            //创建condition
            Condition condition = reentrantLock.newCondition();
            //线程1
            new Thread(() -> {
                try {
                    reentrantLock.lock();
                    log.info("wait signal"); // 1
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("get signal"); // 4
                reentrantLock.unlock();
            }).start();
            //线程2
            new Thread(() -> {
                reentrantLock.lock();
                log.info("get lock"); // 2
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //发送信号
                condition.signalAll();
                log.info("send signal"); // 3
                reentrantLock.unlock();
            }).start();
    }
}
