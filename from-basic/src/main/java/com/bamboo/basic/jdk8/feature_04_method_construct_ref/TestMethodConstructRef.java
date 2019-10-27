package com.bamboo.basic.jdk8.feature_04_method_construct_ref;

import com.bamboo.basic.jdk8.feature_03_functionInterface.Converter;

/**
 * 测试jdk8新特性--方法与构造函数引用
 *   Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用，上面的代码展示了如何引用一个静态方法，我们也可以引用一个对象的方法：
 *   ref https://blog.csdn.net/kegaofei/article/details/80582356
 *   https://my.oschina.net/wangbo888/blog/1942354?from=timeline
 * Created by bamboo on 2019-10-27.
 */
public class TestMethodConstructRef {

    public static void main(String[] args) {

        //---使用::引用静态方法
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Converter<String, Integer> converter = Integer::valueOf;
        Integer intConverter = converter.convert("1234");
        System.out.println(intConverter);


        //---使用::引用实例方法
        Converter<String, StringBuffer> refConverter = new StringBuffer()::append;
        StringBuffer retRes = refConverter.convert("aaaa");
        System.out.println(retRes);


        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Bamboo", "Moon");
        System.out.println(person);
    }

}
