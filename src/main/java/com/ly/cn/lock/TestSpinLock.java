package com.ly.cn.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/6 11:33
 **/
public class TestSpinLock {
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnLock();
            }
        },"A").start();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnLock();
            }
        },"B").start();
    }
}
