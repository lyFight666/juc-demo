package com.ly.cn.function;

import java.util.function.Function;

/**
 * @Author: Mr.L
 * @Date: 2021/3/3 11:43
 **/
public class FunctionDemo1 {
    public static void main(String[] args) {
//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function function = (str)->{return str;};
        System.out.println(function.apply("123"));
    }
}
