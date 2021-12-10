package com.zjq.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 模拟多个任务并发执行完毕后等待主线程发令同时执行后续操作
 *
 * @author zjq
 */
@Slf4j
public class CountDownLatchExample4 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //所有线程阻塞在这，等待主线程号令
                    log.info(Thread.currentThread().getName() + "已准备完毕！！");
                    countDownLatch.await();
                    log.info("【" + Thread.currentThread().getName() + "】" + "开始执行……");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // 主线程准备发令
        Thread.sleep(2000);
        log.info(Thread.currentThread().getName() + "发号施令，给我冲！！");
        // 主线程：执行发令
        countDownLatch.countDown();
    }

}
