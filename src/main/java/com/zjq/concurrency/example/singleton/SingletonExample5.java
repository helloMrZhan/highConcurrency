package com.zjq.concurrency.example.singleton;

import com.zjq.concurrency.anno.NotThreadSafe;

/**
 * 饿汉模式
 * 单例的实例在类装载时进行创建
 *
 * @author zjq
 */
@NotThreadSafe
public class SingletonExample5 {

    /**
     * 私有构造函数
     */
    private SingletonExample5() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample5 instance = new SingletonExample5();

    /**
     * 静态的工厂方法
     */
    public static SingletonExample5 getInstance() {
        return instance;
    }
}
