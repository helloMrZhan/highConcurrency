package com.zjq.concurrency.example.singleton;

import com.zjq.concurrency.anno.Recommend;
import com.zjq.concurrency.anno.ThreadSafe;

/**
 * 枚举模式：最安全
 *
 * @author zjq
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    /**
     * 私有构造函数
     */
    private SingletonExample7() {

    }

    /**
     * 静态的工厂方法
     */
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    public enum Singleton {
        INSTANCE;

        private SingletonExample7 singletonExample7;

        /**
         * JVM保证这个方法绝对只调用一次
         */
        Singleton() {
            singletonExample7 = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singletonExample7;
        }
    }
}