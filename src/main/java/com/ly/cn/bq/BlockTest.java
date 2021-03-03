package com.ly.cn.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 15:41
 **/
public class BlockTest {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }
    // 抛出异常
    public static void test1(){
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());// 队列首位
        // 元素已满，再添加
        // 抛出异常：java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("d"));
        System.out.println("=========");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // 元素已空，此时再移除
        // 抛出异常：java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());
    }

    // 有返回值，不会抛出异常
    public static void test2(){
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.peek());// 队列首位
        // 元素已满，再添加
        // 返回 false 不会抛出异常！
//        System.out.println(blockingQueue.offer("d"));

        System.out.println("=========");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 元素已空，此时再移除
        // 返回 null 不会抛出异常！
        System.out.println(blockingQueue.poll());
    }

    // 等待，阻塞（一直阻塞）
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        // 元素已满，再添加
        // 队列满了，一直阻塞
//        blockingQueue.put("d");

        System.out.println("=========");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // 元素已空，此时再移除
        // 队列空了，一直阻塞
        System.out.println(blockingQueue.take());
    }

    // 等待，阻塞（超时）
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        // 元素已满，再添加
        // 队列已满，等待添加，如果超时了就直接退出
        System.out.println(blockingQueue.offer("d",2, TimeUnit.SECONDS));



        System.out.println("=========");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 元素已空，此时再移除
        // 队列已空，等待添加，如果超时了就直接退出
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
    }
}
