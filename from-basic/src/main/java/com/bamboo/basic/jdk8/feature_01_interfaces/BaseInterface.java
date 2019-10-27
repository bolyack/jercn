package com.bamboo.basic.jdk8.feature_01_interfaces;

/**
 * JDK18--特性1--接口的默认方法
 * Created by bamboo on 2019-10-27.
 */
public interface BaseInterface {


    double addSum(int a ,int b);


    /**
     * Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法，示例如下
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }


}
