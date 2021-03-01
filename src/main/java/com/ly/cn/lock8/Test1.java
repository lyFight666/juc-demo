package com.ly.cn.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/1 15:49
 * 8锁：锁的8个问题
 * 1、标准情况下，两个线程谁先打印 发短信还是打电话？ ans:1、发短信 2、打电话
 * 2、sendMsg延迟4秒，两个线程谁先打印 发短信还是打电话？ ans:1、发短信 2、打电话
 *
 **/
public class Test1 {
    public static void main(String[] args) {
        Phone1 phone1 = new Phone1();
        // 锁的存在
        new Thread(()->phone1.sendMsg()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->phone1.callTel()).start();
    }
}
class Phone1{
    // synchronized 锁的是方法的调用者
    // 两个方法持有的都是同一把锁，谁先拿到谁执行
    public synchronized void sendMsg(){
        try {
            // 延迟4秒
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void callTel(){
        System.out.println("打电话");
    }
}