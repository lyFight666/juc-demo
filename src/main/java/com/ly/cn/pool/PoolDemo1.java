package com.ly.cn.pool;

import java.util.concurrent.*;

/**
 * @Author: Mr.L
 * @Date: 2021/3/3 8:53
 * Executors工具类，4大拒绝策略
 * new ThreadPoolExecutor.AbortPolicy() // 银行满了，再进人就不处理，直接抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy() // 队列满了，哪里来的回哪里去
 * new ThreadPoolExecutor.DiscardPolicy() // 队列满了，直接丢弃任务，不会抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试和最早的线程竞争，争不到也会被丢弃，不会抛出异常
 *
 **/
public class PoolDemo1 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 固定容量的线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();// 可伸缩的线程池（遇强则强，遇弱则弱）
        System.out.println(Runtime.getRuntime().availableProcessors());
        int processors = Runtime.getRuntime().availableProcessors();
        // 自定义线程池，工作中使用ThreadPoolExecutor创建线程池！
        ExecutorService threadPool = new ThreadPoolExecutor(
                5,
                processors,
                3,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        try {
            for (int i = 0; i < 20; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"======> OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
