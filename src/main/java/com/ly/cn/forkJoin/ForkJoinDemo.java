package com.ly.cn.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Author: Mr.L
 * @Date: 2021/3/4 10:03
 * 如何使用forkJoin
 * 1、使用ForkJoinPool来执行
 * 2、计算任务forkJoinPool.execute(ForkJoinTask task)
 * 3、计算类要继承ForkJoinTask
 **/
public class ForkJoinDemo extends RecursiveTask<Long> {
    // 临界值
    private Long temp = 10000L;
    private Long star;
    private Long end;

    public ForkJoinDemo(Long star,Long end){
        this.star = star;
        this.end = end;
    }
    // 计算方法
    @Override
    protected Long compute() {
        if ((end-star) < temp){// 常规求和
            Long sum = 0L;
            for (Long i = star; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{// 分支合并 递归
            Long middle = (star+end)/2;// 中间值
            ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(star, middle);
            forkJoinDemo1.fork();// 拆分任务，将任务压入线程队列
            ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(middle, end);
            forkJoinDemo2.fork();// 拆分任务，将任务压入线程队列
            return forkJoinDemo1.join()+forkJoinDemo2.join();
        }
    }
}
