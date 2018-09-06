package com.bamboo.ebooks.design_pattern.singleton;


/**
 *
 *  枚举方法
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
}
