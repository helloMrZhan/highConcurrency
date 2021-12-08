package com.zjq.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.zjq.concurrency.anno.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @author zjq
 * @date 2021/12/6 21:46
 * <p>title:不可变对象案例</p>
 * <p>description:</p>
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private final static Integer a = 1;
    private final static String b = "2";
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        //Collections.unmodifiable** 处理过的对象不能再修改
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //编译通过但是执行会报错
        Integer put = map.put(1, 3);
        log.info("{}", map.get(1));
    }

}
