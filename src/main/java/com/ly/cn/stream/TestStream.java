package com.ly.cn.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Mr.L
 * @Date: 2021/3/3 15:59
 * 题目要求：1分钟内完成此题
 * 现在又5个用户，完成如下筛选：
 * 1、ID必须是偶数
 * 2、年龄必须大于23
 * 3、用户名转为大写字母
 * 4、用户名字母倒序
 * 5、只输出一个用户
 **/
public class TestStream {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 20,"男","1");
        User u2 = new User(2, "b", 21,"女","2");
        User u3 = new User(3, "a", 23,"女","3");
        User u4 = new User(4, "d", 25,"男","5");
        User u5 = new User(5, "c", 22,"男","2");
        User u6 = new User(6, "d", 23,"女","3");
        User u7 = new User(7, "b", 25,"男","7");
        User u8 = new User(8, "a", 20,"男","6");
        User u9 = new User(9, "c", 23,"女","5");
        User u10 = new User(10, "d", 22,"男","2");

        // 集合就是存储
        List<User> userList = Arrays.asList(u1, u2, u3, u4, u5);
        // 计算交给Stream流
        // lambda表达式、链式编程、函数式接口、Stream流式计算
        userList.stream()
                .filter((u)->{return u.getId()%2==0;})          // b、d
                .filter((u)->{return u.getAge()>23;})           // b、d
                .map((u)->{return u.getName().toUpperCase();})  // B、D
                .sorted((uu1,uu2)->{return uu2.compareTo(uu1);})// D、B
                .limit(1)
        .forEach(System.out::println);
        Stream.concat(userList.stream(),userList.stream())
                .forEach(System.out::println);

    }
}
