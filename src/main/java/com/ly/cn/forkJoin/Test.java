package com.ly.cn.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Author: Mr.L
 * @Date: 2021/3/4 10:17
 * 程序员分
 * 3000（普通） 6000（分支合并） 9000（Stream并行流）
 * 使用分支合并：
 * 1、创建ForkJoinPool
 * 2、创建ForkJoinTask
 * 3、调用ForkJoinPool的submit/execute（无返回值）方法
 * 4、调用get方法获取结果
 **/
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();// 耗时7658
        test2();// 耗时5529
        test3();// 耗时1315
    }
    public static void test1(){
        // 奇怪：如果此处的Long都换成long，则此方法的耗时将大大减少！原因：自动装包耗时
        Long sum = 0L ;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum= "+sum+"，耗时："+(end-start));

    }
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinTask = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTask);// 提交任务
        Long sum = submit.get();// 获取结果

        long end = System.currentTimeMillis();
        System.out.println("sum= "+sum+"，耗时："+(end-start));

    }
    public static void test3(){
        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum= "+sum+"，耗时："+(end-start));

    }
}
