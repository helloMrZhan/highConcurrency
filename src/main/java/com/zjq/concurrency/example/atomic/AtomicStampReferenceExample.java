package com.zjq.concurrency.example.atomic;

import com.zjq.concurrency.anno.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zjq
 * @date 2021/12/5 12:34
 * <p>title:AtomicStampReferenceExample 解决CAS的ABA问题</p>
 * <p>description:</p>
 */
@Slf4j
@ThreadSafe
public class AtomicStampReferenceExample {

    private static AtomicStampedReference<Integer> count = new AtomicStampedReference<>(0,2);

    public static void main(String[] args) {
        //如果是0则更新成3 true
        count.compareAndSet(0,3,2,4);
        //如果是3则更新成3 true
        count.compareAndSet(3,6,5,7);
        //如果是5则更新成7 false
        count.compareAndSet(5,7,5,7);
        //如果是6则更新成8 true
        count.compareAndSet(6,8,3,7);
        //如果是9则更新成88 false
        count.compareAndSet(9,88,7,99);
        log.info("reference:{},stamp:{}",count.getReference(),count.getStamp());
    }
}
