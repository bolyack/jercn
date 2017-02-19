package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/19.
 */
public class Usage_01_ClassicSingletonTest {

    public static void main(String[] args) {
        Usage_01_ClassicSingleton cs1 = Usage_01_ClassicSingleton.getInstance();
        Usage_01_ClassicSingleton cs2 = Usage_01_ClassicSingleton.getInstance();
        System.out.println(cs1 == cs2);
        System.out.println(cs1.equals(cs2));
    }

}
