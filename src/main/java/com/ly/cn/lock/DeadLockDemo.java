package com.ly.cn.lock;

/**
 * @Author: Mr.L
 * @Date: 2021/3/6 11:49
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        new Thread(new MyThread("lockA","lockB")).start();
        new Thread(new MyThread("lockB","lockA")).start();
    }
}
class MyThread implements Runnable{
    private String lock1;
    private String lock2;
    public MyThread(){}
    public MyThread(String lock1,String lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }
    
    @Override
    public void run() {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+"获取到锁"+lock1+"，想获取"+lock2);
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+"获取到锁"+lock2);
            }
        }

    }
}
