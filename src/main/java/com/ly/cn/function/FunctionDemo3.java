package com.ly.cn.function;

import java.util.function.Consumer;

/**
 * @Author: Mr.L
 * @Date: 2021/3/3 15:47
 **/
public class FunctionDemo3 {
    public static void main(String[] args) {
//        Consumer consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        // 消费型接口：指定参数类型
        // Consumer consumer = (s)->{ System.out.println(s); };此句代码，继续简化，如下
        Consumer consumer = System.out::println;
        consumer.accept("123");
    }
}
