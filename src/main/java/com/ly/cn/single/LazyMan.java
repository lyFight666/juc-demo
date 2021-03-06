package com.ly.cn.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: Mr.L
 * @Date: 2021/3/5 9:09
 **/
public class LazyMan {
    private static LazyMan lazyMan;
    private static boolean flag = false;// 创建一个标识符
    // 构造器私有化
    private LazyMan(){
        synchronized(LazyMan.class){
            if(flag==false){
                flag = true;
            }else{
                throw new RuntimeException("不要企图使用反射创建对象！");
            }
        }
        System.out.println(Thread.currentThread().getName()+"===>创建对象");
    }

    public static LazyMan getInstance(){
        if(lazyMan==null){
            synchronized(LazyMan.class){
                if(lazyMan==null){
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }
}
class TestLazyMan{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        LazyMan lazyMan = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructors = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructors.setAccessible(true);
        LazyMan lazyMan = declaredConstructors.newInstance();
        LazyMan lazyMan1 = declaredConstructors.newInstance();
        System.out.println(lazyMan);
        System.out.println(lazyMan1);
    }
}
