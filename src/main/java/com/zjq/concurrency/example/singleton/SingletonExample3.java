package com.zjq.concurrency.example.singleton;

import com.zjq.concurrency.anno.NotThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 * 由于指令重排的存在，有可能返回没有初始化的对象
 */
@NotThreadSafe
public class SingletonExample3 {

    /**
     * 私有构造函数
     */
    private SingletonExample3() {

    }

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排

    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象

    /**
     * 单例对象
      */
    private static SingletonExample3 instance = null;

    /**
     * 静态的工厂方法
     */
    public static SingletonExample3 getInstance() {
        // 双重检测机制
        if (instance == null) {
            // 同步锁
            synchronized (SingletonExample3.class) {
                if (instance == null) {
                    instance = new SingletonExample3();
                }
            }
        }
        return instance;
    }
}