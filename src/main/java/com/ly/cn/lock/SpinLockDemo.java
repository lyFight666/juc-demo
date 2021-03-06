package com.ly.cn.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: Mr.L
 * @Date: 2021/3/6 11:28
 **/
public class SpinLockDemo {
    // 如果是int 默认值0
    // 此处是Thread 对象，默认值null
    AtomicReference atomicReference = new AtomicReference<Thread>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"===>myLock");
        // 如果当前的期望值不是null，就一直自旋
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"===>myUnLock");
        atomicReference.compareAndSet(thread,null);
    }
}
