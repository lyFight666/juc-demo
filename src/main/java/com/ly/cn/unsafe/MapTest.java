package com.ly.cn.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 9:30
 **/
// java.util.ConcurrentModificationException
public class MapTest {
    public static void main(String[] args) {
        // Map是这样用的吗？不是，工作中不用HashMap
        // 默认等价于什么？Map<Object, Object> map = new HashMap<>(16,0.75);
        Map<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
