package com.bamboo.ebooks.design_pattern.singleton;


/**
 * 【单线程写法--懒汉法】 （不好）
 *
 * 这种写法是最简单的，由私有构造器和一个公有静态工厂方法构成，确保每次都只创建一个，避免重复创建。
 * 在工厂方法中对singleton进行null判断，如果是null就new一个出来，最后返回singleton对象。这种方法可以实现延时加载。
 * !!!!!!! 但是有一个致命弱点：线程不安全。如果有两条线程同时调用getSingleton()方法，就有很大可能导致重复创建对象。
 */
public class Singleton_01 {

    private static Singleton_01 instance = null;

    private Singleton_01(){}

    public static Singleton_01 getInstance() {
        if (instance == null) {
            System.out.println(1);
            instance = new Singleton_01();
        }
        return instance;
    }

}
