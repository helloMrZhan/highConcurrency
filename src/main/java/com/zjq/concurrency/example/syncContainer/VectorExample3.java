package com.zjq.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

public class VectorExample3 {

    // java.util.ConcurrentModificationException

    /**
     * 不支持在foreach循环中删除某条数据，执行会报错java.util.ConcurrentModificationException
     * @param v1
     */
    private static void test1(Vector<String> v1) { // foreach
        for(String i : v1) {
            if ("3".equals(i)) {
                v1.remove(i);
            }
        }
    }

    /**
     *   不支持在迭代器循环中删除某条数据，执行会报错java.util.ConcurrentModificationException
     * @param v1
     */
    private static void test2(Vector<String> v1) { // iterator
        Iterator<String> iterator = v1.iterator();
        while (iterator.hasNext()) {
            String i = iterator.next();
            if ("3".equals(i)) {
                //java.util.ConcurrentModificationException
                v1.remove(i);
                //success
                //iterator.remove();
            }
        }
        System.out.println(v1.size());
    }

    /**
     *   支持在for循环中通过remove(int index)和remove(Object o),删除某条数据，但是不推荐，推荐还是用迭代器删除
     * @param v1
     */
    private static void test3(Vector<String> v1) { // for
        for (int i = 0; i < v1.size(); i++) {
            if ("3".equals(v1.get(i))) {
                v1.remove(i);
                //v1.remove(v1.get(i));
            }
        }
        System.out.println(v1.size());
    }

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        test3(vector);
    }
}
