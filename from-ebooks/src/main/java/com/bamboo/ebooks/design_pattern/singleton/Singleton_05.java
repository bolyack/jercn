package com.bamboo.ebooks.design_pattern.singleton;

/**
 * 静态内部内。（建议使用）
 * 定义一个私有的内部类，在第一次用这个嵌套类时，会创建一个实例。而类型为SingletonHolder的类，
 * 只有在Singleton.getInstance()中调用，由于私有的属性，他人无法使用SingleHolder，
 * 不调用Singleton.getInstance()就不会创建实例。
 * 优点：达到了lazy loading的效果，即按需创建实例。
 */
public class Singleton_05 {

    private Singleton_05(){}

    /**
     * 有没有一种延时加载，并且能保证线程安全的简单写法呢？
     * 我们可以把Singleton实例放到一个静态内部类中，这样就避免了静态实例在Singleton类加载的时候就创建对象，
     * 并且由于静态内部类只会被加载一次，所以这种写法也是线程安全的：
     */
    private static class SingletonHolder {
        private final static Singleton_05 instance = new Singleton_05();
    }

    public static Singleton_05 getInstance() {
        return SingletonHolder.instance;
    }


}
