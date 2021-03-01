package com.ly.cn;

/**
  * @Author: Mr.L
  * @Date: 2021/3/1 11:14
**/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}
// 手写代码口诀：等待，业务，通知
class Data{
    private int num = 0;
    public synchronized void increment() throws InterruptedException {
        while(num!=0){
            // 等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"======>"+num);
        // 通知（唤醒）
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while(num==0){
            // 等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"======>"+num);
        // 通知
        this.notifyAll();
    }
}
