package com.ly.cn.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Mr.L
 * @Date: 2021/3/5 13:03
 **/
public class CASDemo {
    // CAS compareAndSet：比较并交换
    // AtomicStampedReference 如果泛型是一个包装类，注意对象的引用问题
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2020);
        // 期望、更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果我的期望值达到了，就更新；否则就不更新。CAS是CPU的并发原语！
        // =========捣乱的线程=========
//        System.out.println(atomicInteger.compareAndSet(2020, 2021));
//        System.out.println(atomicInteger.get());// 获取值
//        System.out.println(atomicInteger.compareAndSet(2021, 2020));
//        System.out.println(atomicInteger.get());// 获取值
//        // =========期望的线程=========
//        System.out.println(atomicInteger.compareAndSet(2020, 2022));
//        System.out.println(atomicInteger.get());// 获取值
        // 正常业务操作时，这里边比较的都是一个对象
        AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<>(123,1);
        new Thread(()->{
            int stamp = atomicReference.getStamp();
            System.out.println("a1===> "+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ReentrantLock reentrantLock = new ReentrantLock();
            // 如果期望值是123，就将其改为125；期望版本号是当前atomicReference.getStamp()的值，就将其加1
            System.out.println("a2修改成功？===> "+atomicReference.compareAndSet(123, 125,
                    atomicReference.getStamp(), atomicReference.getStamp() + 1));
            System.out.println("a2版本号===> "+atomicReference.getStamp());

            // 如果期望值是125，就将其改为123；期望版本号是当前atomicReference.getStamp()的值，就将其加1
            System.out.println("a3修改成功？===> "+atomicReference.compareAndSet(125, 123,
                    atomicReference.getStamp(), atomicReference.getStamp() + 1));
            System.out.println("a3版本号===> "+atomicReference.getStamp());
        }).start();

        new Thread(()->{
            int stamp = atomicReference.getStamp();
            System.out.println("b1===> "+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 如果期望值是123，就将其改为100
            System.out.println("b1修改成功？===> "+atomicReference.compareAndSet(123, 100,
                    atomicReference.getStamp(), atomicReference.getStamp() + 1));
            System.out.println("此时b2版本号===> "+atomicReference.getStamp());
        }).start();
    }
}
