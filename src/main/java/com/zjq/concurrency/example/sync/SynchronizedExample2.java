package com.zjq.concurrency.example.sync;

import com.zjq.concurrency.anno.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zjq
 * @date 2021/12/5 16:19
 * <p>title:synchronized案例 修饰类和静态方法</p>
 * <p>description:</p>
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample2 {


    /**
     * 修饰一个类
     * @param i
     */
    public void test1(int i){
        synchronized (SynchronizedExample2.class){
            for (int j = 0; j < 10; j++) {
                log.info("test1 i={},j={}",i,j);
            }
        }
    }

    /**
     * 修饰一个静态方法
     * @param i
     */
    public static synchronized void  test2(int i){
        for (int j = 0; j < 10; j++) {
            log.info("test2 i={},j={}",i,j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //启动两个线程池验证修饰类锁定的是所有的对象，一个执行完毕才会执行另一个
//        executorService.execute(()->{
//            synchronizedExample1.test1(1);
//        });
//        executorService.execute(()->{
//            synchronizedExample1.test1(2);
//        });
        //设置两个不同的调用对象可以看到输出结果是一个执行完毕才会执行另一个
        executorService.execute(()->{
            synchronizedExample1.test1(1);
        });
        executorService.execute(()->{
            synchronizedExample2.test1(2);
        });
    }
}
