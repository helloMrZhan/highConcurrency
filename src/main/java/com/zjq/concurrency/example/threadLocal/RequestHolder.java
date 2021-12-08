package com.zjq.concurrency.example.threadLocal;

/**
 * <p>自定义RequestHolder</p></p>
 *
 * @Author zjq
 * @Date 2021/12/7
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void set(Long id) {
        requestHolder.set(id);
    }

    public static Long get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }

}
