package com.zjq.concurrency.example.publish;

import com.zjq.concurrency.anno.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 一个类中有一个私有的容器，并为其提供getter方法，容器中值是可以改变的
 * 存在多线程并发操作安全隐患
 * @author zjq
 * @date 2021/12/5 16:19
 * <p>title:不安全发布对象实例</p>
 */
@Slf4j
@NotThreadSafe
public class UnSafePublish {
    private String[] states = {"111","222","333"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish();
        log.info("{}", Arrays.toString(unSafePublish.getStates()));

        unSafePublish.getStates()[0] = "666";
        log.info("{}", Arrays.toString(unSafePublish.getStates()));
    }
}
