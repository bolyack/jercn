package com.bamboo.ebooks.design_pattern.singleton;


/**
 *
 *  枚举方法(推荐)
 *  Effective Java作者Josh Bloch 提倡的方式，在我看来简直是来自神的写法。解决了以下三个问题：
 * (1)自由序列化。
 * (2)保证只有一个实例。
 * (3)线程安全。
 */
public enum Singleton_06 {

    INSTANCE,
    ;

    public void otherMethods() {
        System.out.println("Something");
    }

    /**
     * 前面的Singleton_01 ~ Singleton_05 的单例所有实现方式都有两个共同的缺点：
     * 1)都需要额外的工作(Serializable、transient、readResolve())来实现序列化，
     *    否则每次反序列化一个序列化的对象实例时都会创建一个新的实例。
     * 2)可能会有人使用反射强行调用我们的私有构造器（如果要避免这种情况，可以修改构造器，让它在创建第二个实例的时候抛异常）。
     *
     * 有一种更加优雅的方法来实现单例模式，那就是枚举写法
     */


    /**
     * 代码没有一劳永逸的写法，只有在特定条件下最合适的写法。在不同的平台、不同的开发环境（尤其是jdk版本）下，自然有不同的最优解（或者说较优解）。
     * 比如枚举，虽然Effective Java中推荐使用，但是在Android平台上却是不被推荐的。在这篇Android Training中明确指出：
     *
     * Enums often require more than twice as much memory as static constants. You should strictly avoid using enums on Android.
     *
     * 再比如双重检查锁法，不能在jdk1.5之前使用，而在Android平台上使用就比较放心了（一般Android都是jdk1.6以上了，不仅修正了volatile的语义问题，还加入了不少锁优化，使得多线程同步的开销降低不少）。
     *
     * 最后，不管采取何种方案，请时刻牢记单例的三大要点：
     *
     * 线程安全
     * 延迟加载
     * 序列化与反序列化安全
     */
}
