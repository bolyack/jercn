package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/19.
 */
public class Usage_01_ClassicSingletonTest {

    public static void main(String[] args) {
        simpleTest();
//        threadTest();
    }

    /**
     * 简单测试
     */
    public static void simpleTest() {
        Usage_01_ClassicSingleton cs1 = Usage_01_ClassicSingleton.getInstance();
        Usage_01_ClassicSingleton cs2 = Usage_01_ClassicSingleton.getInstance();
        System.out.println(cs1 == cs2);
        System.out.println(cs1.equals(cs2));
    }

    /**
     * TODO 待验证经典单例模式(延迟实例化)下非线程安全的问题
     */
    public static void threadTest() {

    }

}


