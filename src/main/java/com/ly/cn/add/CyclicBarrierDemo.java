package com.ly.cn.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 10:41
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 集齐7颗龙珠召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙！");
        });
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            // lambda能操作到i吗？不能，只能通过临时变量
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"颗龙珠");
                try {
                    cyclicBarrier.await();// 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
