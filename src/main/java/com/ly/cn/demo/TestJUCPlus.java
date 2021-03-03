package com.ly.cn.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
  * @Author: Mr.L
  * @Date: 2021/3/1 14:56
 * 顺序执行，A === > B ===> C
**/
public class TestJUCPlus {

    public static void main(String[] args) throws InterruptedException {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            try {
                data.print2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"B").start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            try {
                data.print3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"C").start();

    }

}

class Data3{
    private int num = 1;// 1A 2B 3C 依次
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    public void print1() throws InterruptedException {
        lock.lock();
        try {
            while(num!=1){
                // 等待
                condition1.await();
            }
            num = 2;
            System.out.println(Thread.currentThread().getName()+"======>AAAAAAAAA");
            // 通知（唤醒）指定线程
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print2() throws InterruptedException {
        lock.lock();
        try {
            while(num!=2){
                // 等待
                condition2.await();
            }
            num = 3;
            System.out.println(Thread.currentThread().getName()+"======>BBBBBBBBB");
            // 通知（唤醒）指定线程
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print3() throws InterruptedException {
        lock.lock();
        try {
            while(num!=3){
                // 等待
                condition3.await();
            }
            num = 1;
            System.out.println(Thread.currentThread().getName()+"======>CCCCCCCCC");
            // 通知（唤醒）指定线程
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
