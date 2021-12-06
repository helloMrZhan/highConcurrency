package com.zjq.concurrency.example.singleton;

import com.zjq.concurrency.anno.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 存在线程安全隐患 在静态方法上加synchronized关键字能够保证线程安全
 * 但影响了性能 在并发获取该单例实例调用该静态方法时
 * 所以需要将synchronized关键字下沉到方法体中的判空里面去
 *
 * @author zjq
 */
@NotThreadSafe
public class SingletonExample2 {

    /**
     * 私有构造函数
     */
    private SingletonExample2() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample2 instance = null;

    /**
     * 静态的工厂方法
     */
    public synchronized static SingletonExample2 getInstance() {
        if (instance == null) {
            instance = new SingletonExample2();
        }
        return instance;
    }
}
