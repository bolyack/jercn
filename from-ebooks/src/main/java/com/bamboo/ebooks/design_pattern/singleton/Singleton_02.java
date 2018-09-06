package com.bamboo.ebooks.design_pattern.singleton;

/**
 * 饿汉法 （建议使用）
 * 顾名思义，饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。
 * 初试化静态的instance创建一次。如果我们在Singleton类里面写一个静态的方法不需要创建实例，它仍然会早早的创建一次实例。
 * 这样做的好处是编写简单，但是无法做到延迟创建对象。
 * 但是我们很多时候都希望对象可以尽可能地延迟加载，从而减小负载
 */
public class Singleton_02 {

    private static Singleton_02 instance = new Singleton_02();
    private Singleton_02(){}

    public static Singleton_02 getInstance() {
        return instance;
    }

}
