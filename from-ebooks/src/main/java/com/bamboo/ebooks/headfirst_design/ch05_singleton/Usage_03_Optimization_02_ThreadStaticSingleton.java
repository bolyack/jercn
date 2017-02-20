package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/20.
 * 优化单例多线程问题——使用“急切”(饿汉模式)创建实例，而不用延迟实例化的做法
 *
 * 如果应用程序总是创建并使用单例，或者在创建和运行时方面的负担不太繁重，你可能想要急切创建此单例。
 */
public class Usage_03_Optimization_02_ThreadStaticSingleton {

    //在静态初始化器中创建单例。这段代码保证了线程安全
    private static Usage_03_Optimization_02_ThreadStaticSingleton uniqueInstance = new Usage_03_Optimization_02_ThreadStaticSingleton();

    private Usage_03_Optimization_02_ThreadStaticSingleton(){}

    public static Usage_03_Optimization_02_ThreadStaticSingleton getInstance() {
        //已经有实例了，直接使用它
        return uniqueInstance;
    }

    /**
     *  接 Usage_02_ThreadSingleton
     *  【缺点发现】
     *  利用这个做法，我们依赖JVM在加载这个类时马上创建此唯一的单例实例。JVM保证在任何线程访问uniqueInstance静态变量之前，一定先创建此实例。
     */

}
