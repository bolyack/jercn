package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/20.
 * 单例(处理多线程)
 *
 * 接上个类 Usage_01_ClassicSingleton 的问题。
 * 假若有两个线程同时调用Singleton.getInstance()，而这时uniqueInstance还没有初始化，那么有可能会出现调用Singleton.getInstance()方法返回不同的实例。
 * 只要把getInstance()变成同步方法，多线程灾难几乎就可以轻易地解决了。
 */
public class Usage_02_ThreadSingleton {

    private static Usage_02_ThreadSingleton uniqueInstance;

    private Usage_02_ThreadSingleton(){}

    /**
     * 通过增加【synchronized】关键字到getInstance()方法中，我们迫使每个线程在进入这个方法之前，要先等候别的线程离开该方法。
     * 也就是说，不会有两个线程可以同时进入这个方法。
     * @return
     */
    public static synchronized Usage_02_ThreadSingleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Usage_02_ThreadSingleton();
        }
        return uniqueInstance;
    }

    /**
     * 【缺点发现】
     *  上面的代码可以解决多线程的问题，但是同步会降低性能，这不又是另一个问题吗？
     *  只有第一次执行此方法时，才真正需要同步，一旦设置好uniqueInstance变量，就不需要同步这个方法了。之后每次调用这个方法，同步都是一种累赘和性能耗费。
     */

}
