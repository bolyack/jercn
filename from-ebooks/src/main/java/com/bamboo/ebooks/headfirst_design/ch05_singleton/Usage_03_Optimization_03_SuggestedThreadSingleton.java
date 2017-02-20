package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/20.
 * 优化单例多线程问题——用“双重检查加锁”，在getInstance()中减少使用同步
 *
 * 利用“双重检查加锁”，在getInstance（）中减少使用同步，
 * 利用双重检查加锁，首先检查是否实例已经创建了，如果尚未创建，才进行同步。这样一来，只有第一次会同步，这正是我们想要的。
 *
 * 如果性能是你关心的重点，那么这个做法可以帮你大大地减少getInstance()的时间耗费。
 */
public class Usage_03_Optimization_03_SuggestedThreadSingleton {

    //【volatile】关键词确保，当uniqueInstance变量被初始化成Singleton实例时，
    //多个线程正确地处理uniqueInstance变量。
    private volatile static Usage_03_Optimization_03_SuggestedThreadSingleton uniqueInstance;

    private Usage_03_Optimization_03_SuggestedThreadSingleton(){}

    public static Usage_03_Optimization_03_SuggestedThreadSingleton getInstance() {
        //检查实例，如果不存在，就进入同步区块。
        if (null == uniqueInstance) {
            //注意，只有第一次才彻底执行这里的代码
            synchronized (Usage_03_Optimization_03_SuggestedThreadSingleton.class) {
                //进入区块后，再检查一次。如果仍是nul，才创建实例，
                if (null == uniqueInstance) {
                    uniqueInstance = new Usage_03_Optimization_03_SuggestedThreadSingleton();
                }
            }
        }
    }

}
