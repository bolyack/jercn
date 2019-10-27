package com.bamboo.basic.jdk8.feature_03_functionInterface;

/**
 * 测试jdk8新特性--函数式接口
 * Created by bamboo on 2019-10-27.
 */
public class TestFunctionInterface {

    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer intConver = converter.convert("123");
        System.out.println(intConver);
    }

}
