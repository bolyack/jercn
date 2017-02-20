package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/19.
 * 经典的单例实现(懒汉模式，延迟实例化，非线程安全)
 * @see http://www.importnew.com/23491.html
 */
public class Usage_01_ClassicSingleton {

    //利用一个静态变量来记录Singleton的唯一实例
    private static Usage_01_ClassicSingleton uniqueInstance;

    //把构造器声明为私有的，只有Singleton类内才可以调用构造器。
    private Usage_01_ClassicSingleton(){}

    private static int i = 0;

    /**
     * getInstance()是静态的，这意味着它是一个类方法，所以可以在代码的任何地方使用Singleton.getInstance()访问它。这和访问全局变量一样简单，只是多了个优点：单例可以延迟实例化。
     */
    //用getInstance()方法实例化对象，并返回这个实例
    public static Usage_01_ClassicSingleton getInstance() {
        //如果uniqueInstance是空的，表示还没有创建实例
        if (null == uniqueInstance) {
            //如果uniqueInstance是空的，我们就利用私有的构造器产生一个Singleton实例并把它赋值给uniqueInstance静态变量中。
            //请注意，如果我们不需要这个实例，它就永远不会产生。这就是"延迟实例化"
            uniqueInstance = new Usage_01_ClassicSingleton();
            i++;
            System.out.println(i);
        }
        return uniqueInstance;
    }


    /**
     * 【缺点发现】
     * 本模式存在的缺点——非线程安全
     * 假如有两个线程同时调用Singleton.getInstance()，而这时uniqueInstance还没有初始化，那么有可能会出现调用Singleton.getInstance()方法返回不同的实例。
     */

}
