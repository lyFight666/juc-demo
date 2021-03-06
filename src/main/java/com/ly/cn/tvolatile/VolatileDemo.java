package com.ly.cn.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Mr.L
 * @Date: 2021/3/4 16:12
 **/
public class VolatileDemo {
    // volatile不保证原子性
    // 原子类
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add(){
//        num++;// 不是一个原子性操作
        num.getAndIncrement();// AtomicInteger +1方法 底层是利用CAS实现的
    }
    public static void main(String[] args) {
        // 理论上，执行之后应该是20000
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000 ; j++) {
                    add();
                }
            }).start();
        }
        while(Thread.activeCount()>2){// 默认有main、gc线程；所以是大于2时礼让
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"======> "+num);

    }

}
