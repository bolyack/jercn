package com.bamboo.ebooks.design_pattern.singleton;

public class Singleton_01_Test {

    public static void main(String[] args) {
//        Singleton_01 singleton01 = new Singleton_01();//报错
        Singleton_01 singleton01 = Singleton_01.getInstance();
        System.out.println(singleton01.hashCode());

        //本机没有测试出线程问题，需要使用jmeter
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Singleton_01.getInstance().hashCode());
            }).start();
        }
    }

}
