package com.ly.cn.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 9:48
 **/
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//         new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>(Callable)).start();
        new Thread().start();// 怎么启动Callable

        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask<Integer>(myThread);// 适配类
        new Thread(futureTask).start();
        // 打印返回值
        Integer o = (Integer) futureTask.get();
        System.out.println(o);
    }
}
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 1024;
    }
}