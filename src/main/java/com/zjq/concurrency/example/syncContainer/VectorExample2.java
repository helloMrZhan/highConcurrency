package com.zjq.concurrency.example.syncContainer;

import com.zjq.concurrency.anno.NotThreadSafe;
import com.zjq.concurrency.anno.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 同步容器-Vector(同步容器在多线程情况下不一定是线程安全的)
 * @author zjq
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {


    public static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws Exception {

        //多次调用的情况下可以发现会导致程序报错，因为多线程操作有可能另外一个线程移除了后面线程要获取的值
        while(true) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            /**
             * 分别开启两个线程执行删除和获取操作
             */
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        list.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }


}
