package com.ly.cn.function;

import org.springframework.util.StringUtils;

import java.util.function.Predicate;

/**
 * @Author: Mr.L
 * @Date: 2021/3/3 11:53
 **/
public class FunctionDemo2 {
    public static void main(String[] args) {
//        Predicate predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return StringUtils.isEmpty(s);
//            }
//        };
        Predicate predicate = (s)->{return StringUtils.isEmpty(s);};
        System.out.println(predicate.test("123"));
    }
}
