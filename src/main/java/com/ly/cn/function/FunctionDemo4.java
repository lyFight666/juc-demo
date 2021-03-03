package com.ly.cn.function;

import java.util.function.Supplier;

/**
 * @Author: Mr.L
 * @Date: 2021/3/3 15:52
 **/
public class FunctionDemo4 {
    public static void main(String[] args) {
//        Supplier supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "123";
//            }
//        };
        Supplier supplier = ()->{return "123";};
        System.out.println(supplier.get());
    }
}
