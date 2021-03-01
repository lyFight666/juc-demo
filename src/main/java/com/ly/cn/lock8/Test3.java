package com.ly.cn.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/1 16:31
 * 5、增加两个静态方法 一个对象 先发短信还是先打电话？ans：发短信
 * 6、两个对象，两个静态方法 先发短信还是先打电话？ans：发短信
 **/
public class Test3 {
    public static void main(String[] args) {
        // 两个对象的Class模板是同一个，static锁的是Class
        Phone4 phone = new Phone4();
        // 锁的存在
        new Thread(()->phone.sendMsg()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->phone.callTel()).start();
    }
}
class Phone3{
    // synchronized 锁的是方法的调用者
    // static 静态方法 类一加载就有了 锁的是Class
    public static synchronized void sendMsg(){
        try {
            // 延迟4秒
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void callTel(){
        System.out.println("打电话");
    }
}