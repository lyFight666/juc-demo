package com.ly.cn.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 16:55
 * 同步队列
 * 和其他BlockingQueue不一样  SynchronousQueue不存储元素
 * put了一个，就必须先take一个，才能继续put
 **/
public class SynchronousQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"put 1");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"put 2");
                synchronousQueue.put("3");
                System.out.println(Thread.currentThread().getName()+"put 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"===>"+synchronousQueue.take());
                System.out.println(Thread.currentThread().getName()+"===>"+synchronousQueue.take());
                System.out.println(Thread.currentThread().getName()+"===>"+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
