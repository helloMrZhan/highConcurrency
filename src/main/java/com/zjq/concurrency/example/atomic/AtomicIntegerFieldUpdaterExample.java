package com.zjq.concurrency.example.atomic;

import com.zjq.concurrency.anno.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author zjq
 * @date 2021/12/5 12:51
 * <p>title:AtomicIntegerFieldUpdater</p>
 * <p>description:原子性的去更新某个类的某个字段，字段要求必须是volatile修饰的非static字段</p>
 */
@ThreadSafe
@Slf4j
public class AtomicIntegerFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterExample> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterExample.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicIntegerFieldUpdaterExample atomicIntegerFieldUpdaterExample = new AtomicIntegerFieldUpdaterExample();
        //如果是100则更新成120
        if (updater.compareAndSet(atomicIntegerFieldUpdaterExample, 100, 120)) {
            log.info("update success 1, {}", atomicIntegerFieldUpdaterExample.getCount());
        }

        if (updater.compareAndSet(atomicIntegerFieldUpdaterExample, 100, 120)) {
            log.info("update success 2, {}", atomicIntegerFieldUpdaterExample.getCount());
        } else {
            log.info("update failed, {}", atomicIntegerFieldUpdaterExample.getCount());
        }
    }
}
