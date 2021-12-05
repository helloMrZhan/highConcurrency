package com.zjq.concurrency.example.publish;

import com.zjq.concurrency.anno.NotRecommend;
import com.zjq.concurrency.anno.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 一个类中有一个私有的容器，并为其提供getter方法，容器中值是可以改变的
 * 存在多线程并发操作安全隐患
 * @author zjq
 * @date 2021/12/5
 * <p>title:对象逸出实例</p>
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
