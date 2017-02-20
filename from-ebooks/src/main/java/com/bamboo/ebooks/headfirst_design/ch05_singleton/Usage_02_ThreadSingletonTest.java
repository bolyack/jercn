package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/20.
 */
public class Usage_02_ThreadSingletonTest {

    public static void main(String[] args) {
        Usage_02_ThreadSingleton us1 = Usage_02_ThreadSingleton.getInstance();
        Usage_02_ThreadSingleton us2 = Usage_02_ThreadSingleton.getInstance();
        System.out.println(us1 == us2);
        System.out.println(us1.equals(us2));
    }

}
