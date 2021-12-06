package com.zjq.concurrency.example.singleton;

import com.zjq.concurrency.anno.ThreadSafe;

/**
 * 懒汉模式 ==> 双重同步锁单例模式 + volatile禁止指令重排序
 * 单例的实例在第一次使用时创建
 * 存在线程安全隐患 在静态方法上加synchronized关键字能够保证线程安全
 * 但影响了性能 在并发获取该单例实例调用该静态方法时
 * 所以需要将synchronized关键字下沉到方法体中的判空里面去
 */
@ThreadSafe
public class SingletonExample4 {
    /**
     * 私有构造函数
     */
    private SingletonExample4() {

    }

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排

    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象

    /**
     * 单例对象 volatile禁止指令重排序
     */
    private volatile static SingletonExample4 instance = null;

    /**
     * 静态的工厂方法
     */
    public static synchronized SingletonExample4 getInstance() {
        //双重检查机制
        if (instance == null) {
            //同步锁
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }

}