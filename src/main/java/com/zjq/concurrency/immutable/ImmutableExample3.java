package com.zjq.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.zjq.concurrency.anno.ThreadSafe;

/**
 * @author zjq
 * @date 2021/12/6 21:46
 * <p>title:不可变对象案例</p>
 * <p>description:</p>
 */
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1, 2).put(3, 4).put(5, 6).build();


    public static void main(String[] args) {
        //不可变对象进行变更操作都会抛出异常
        //运行时，会抛出异常
        list.add(4);
        //运行时，会抛出异常
        set.add(66);
        //运行时，会抛出异常
        map.put(6,8);
        //运行时，会抛出异常
        map2.put(9,8);
        //获取正常
        System.out.println(map2.get(3));
    }
}
