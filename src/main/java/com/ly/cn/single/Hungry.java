package com.ly.cn.single;

/**
 * @Author: Mr.L
 * @Date: 2021/3/5 9:09
 * 浪费内存资源
 **/
public class Hungry {
    private static Hungry hungry = new Hungry();
    // 构造器私有化
    private Hungry(){
    }

    public static Hungry getInstance(){
        return hungry;
    }
}
class Test{
    public static void main(String[] args) {
        System.out.println(Hungry.getInstance());
    }
}
