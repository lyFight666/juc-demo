package com.ly.cn.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 9:14
 **/
// java.util.ConcurrentModificationException 同理可证
public class SetList {
    public static void main(String[] args) {
        /**
         * 解决方案：
         * 1、Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 2、Set<String> set = new CopyOnWriteArraySet<>();
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();

        }
    }
}
