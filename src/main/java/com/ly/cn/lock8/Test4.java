package com.ly.cn.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/1 17:13
 * 7、一个静态同步方法 一个普通同步方法 先发短信还是先打电话？ans：打电话
 * 8、一个静态同步方法 一个普通同步方法 两个对象 先发短信还是先打电话？ans：打电话
 **/
public class Test4 {
    public static void main(String[] args) {
        // 两个对象的Class模板是同一个，static锁的是Class
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        // 锁的存在
        new Thread(()->phone1.sendMsg()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->phone2.callTel()).start();
    }
}
class Phone4{
    // static静态方法，锁的是Class
    public static synchronized void sendMsg(){
        try {
            // 延迟4秒
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    // 普通同步方法，锁的是持有者
    public synchronized void callTel(){
        System.out.println("打电话");
    }
}
