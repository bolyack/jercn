package com.bamboo.ebooks.design_pattern.singleton;

/**
 * "双重检查锁"
 * 加同步锁时，前后两次判断实例是否存在（可行）
 * 这种写法被称为“双重检查锁”，顾名思义，就是在getSingleton()方法中，进行两次null检查。
 * 看似多此一举，但实际上却极大提升了并发度，进而提升了性能。为什么可以提高并发度呢？
 * 在单例中new的情况非常少，绝大多数都是可以并行的读操作。
 * 因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了。
 */
public class Singleton_04 {
    private static volatile Singleton_04 instance = null;
    private Singleton_04(){}

    public static Singleton_04 getInstance() {
        if (null == instance) {
            synchronized (Singleton_04.class) {
                if (null == instance) {
                    instance = new Singleton_04();
                }
            }
        }
        return instance;
    }




}
