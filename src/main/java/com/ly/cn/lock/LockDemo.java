package com.ly.cn.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Mr.L
 * @Date: 2021/3/6 11:00
 **/
// Synchronized
public class LockDemo {
    public static void main(String[] args) {
//        Phone phone = new Phone();
//        new Thread(()->{
//            phone.sendMsg();
//        },"A").start();
//        new Thread(()->{
//            phone.sendMsg();
//        },"B").start();

        Phone1 phone1 = new Phone1();
        new Thread(()->{
            phone1.sendMsg();
        },"C").start();
        new Thread(()->{
            phone1.sendMsg();
        },"D").start();
    }
}
class Phone{
    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName()+"sendMsg");
        call();
    }
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"call");
    }
}

class Phone1{
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    public void sendMsg(){
        lock1.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"sendMsg");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }
    public synchronized void call(){
        lock2.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }
}