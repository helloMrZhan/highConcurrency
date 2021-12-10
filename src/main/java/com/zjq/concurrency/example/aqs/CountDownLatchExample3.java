package com.zjq.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 模拟并发调用多个任务
 *
 * @author zjq
 */
@Slf4j
public class CountDownLatchExample3 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2) {
            @Override
            public void await() throws InterruptedException {
                super.await();
                log.info("其他线程执行完毕后主线程执行的内容");
                log.info("threadName:{},", Thread.currentThread().getName() + " count down is ok");
            }
        };

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    log.info(Thread.currentThread().getName() + "任务已完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //计数器减1
                    countDownLatch.countDown();
                }
            }
        }, "thread111");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    log.info(Thread.currentThread().getName() + "任务已完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //计数器减1
                    countDownLatch.countDown();
                }
            }
        }, "thread222");


        thread1.start();
        thread2.start();

        countDownLatch.await();
        log.info("====everything is end====");
    }

}
