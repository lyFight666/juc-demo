package com.ly.cn.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/4 11:42
 * 异步调用：CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 *
 **/
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 无返回值的runAsync异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"===>runAsync");
//        });
//        System.out.println("111");
//        completableFuture.get();// 获取阻塞执行结果

        // 有返回值的 异步回调
        // 类似于ajax，成功和失败的回调
        // 返回的是错误信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"===>supplyAsync-----Integer");
            int num = 1/0;// 手动制造异常
            return 1024;
        });
        // 打印获取到的结果
        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t===>" + t);// 线程异步正常执行的结果（如果异常就是null）
            System.out.println("u===>" + u);// 线程执行产生的错误信息（如果正常执行就是null）
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 123;// 此处是将错误的结果进行返回
        }).get());// get方法获取结果
    }
}
