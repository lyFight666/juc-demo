package com.ly.cn.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Mr.L
 * @Date: 2021/3/2 14:09
 * 独占锁（写锁） 一次只能被一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 * ReadWriteLock
 * 读-读 可以共存
 * 读-写 不能共存
 * 写-写 不能共存
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyLockCache myCache = new MyLockCache();
        // 写入
        for (int i = 1; i <= 5 ; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            }).start();
        }
        // 读取
        for (int i = 1; i <= 5 ; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            }).start();
        }
    }
}
// 自定义缓存类
class MyLockCache{
    private volatile Map<String,String> map =new HashMap<>();
    // 读写锁：更细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 写入
    public void put(String key,String value){
        // 要保证写的原子性，就要加此锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(key+"写入"+value+"开始");
            map.put(key, value);
            System.out.println(key+"写入"+value+"ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    // 读取
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(key+"读取开始");
            map.get(key);
            System.out.println(key+"读取ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
// 自定义缓存类
class MyCache{
    private volatile Map<String,String> map =new HashMap<>();
    public void put(String key,String value){
        System.out.println(key+"写入"+value+"开始");
        map.put(key, value);
        System.out.println(key+"写入"+value+"ok");
    }
    public void get(String key){
        System.out.println(key+"读取开始");
        map.get(key);
        System.out.println(key+"读取ok");
    }
}