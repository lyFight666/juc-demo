package com.ly.cn.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 8:51
 **/
// java.util.ConcurrentModificationException
public class ListTest {
    public static void main(String[] args) {
        // 并发下，ArrayList不安全吗？Synchronized
        /**
         * 解决方案：
         * 1、List<String> list = new Vector<>();
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         */
        // 现象：多个线程调用的时候，List 读是固定的，写入会覆盖
        // 目的：在写入的时候，避免覆盖造成数据问题
        // CopyOnWrite 写入时复制，简称COM，计算机程序设计领域的一种优化策略
        // CopyOnWriteArrayList 比 Vector牛逼在哪里？前者使用Lock锁，后者使用Synchronized，前者效率更高，且会先复制数据，避免丢失
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();

        }
    }
}
