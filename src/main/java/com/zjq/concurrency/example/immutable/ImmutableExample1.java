package com.zjq.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.zjq.concurrency.anno.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author zjq
 * @date 2021/12/6 21:46
 * <p>title:不可变对象案例</p>
 * <p>description:</p>
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        /**
         * 基础类型不允许在修改
         */
//        a = 2;
//        b = "3";
        /**
         * 引用类型变量，初始化之后就不能再指向另外一个对象，但是可以修改里面的值
         */
//        map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    private void test(final int a) {
        //方法传入final类型的基础类型也不允许修改
//        a = 1;
    }
}
