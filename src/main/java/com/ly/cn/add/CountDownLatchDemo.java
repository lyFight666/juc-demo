package com.ly.cn.add;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 10:23
 **/
// 计算器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 必须要执行任务的时候使用
        // 假设是小学生放学，6个都出门了，门卫大爷才关门
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" Go out!");
                latch.countDown();// 数量减1
            },String.valueOf(i)).start();
        }
        latch.await();// 等待计数器归零，再向下执行
        System.out.println("Close Door!");
    }
}
