package com.ly.cn.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/1 16:02
 * 3、增加了一个普通方法之后，是先发短信还是hello？ ans：普通方法
 * 4、两个对象，两个同步方法，发短信还是打电话？ ans：打电话 两把锁，互不影响，发短信延迟4秒，打电话就先执行了
 **/
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        Phone1 phone1 = new Phone1();
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
class Phone2{
    // synchronized 锁的是方法的调用者
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
    // 这里没有锁，不是同步方法，不受影响
    public void hello(){
        System.out.println("hello");
    }
}
