package com.ly.cn.tvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Mr.L
 * @Date: 2021/3/4 14:39
 **/
public class JMMDemo {
    // 不加volatile会造成死循环
    // 加上volatile可保证可见性
    private volatile static int num = 0;
    public static void main(String[] args) {

        // 假设这就是A线程
        new Thread(()->{
            while(num==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;// 假设这里是B线程，修改num的值
        System.out.println(num);
    }
}
