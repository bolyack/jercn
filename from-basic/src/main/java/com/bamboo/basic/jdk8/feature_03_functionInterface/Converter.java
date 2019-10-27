package com.bamboo.basic.jdk8.feature_03_functionInterface;

/**
 * 函数式接口
 *      Lambda表达式是如何在java的类型系统中表示的呢？每一个lambda表达式都对应一个类型，通常是接口类型。
 *      而“函数式接口”是指仅仅只包含一个抽象方法的接口，每一个该类型的lambda表达式都会被匹配到这个抽象方法。
 *      因为 默认方法 不算抽象方法，所以你也可以给你的函数式接口添加默认方法。
 *
 *      我们可以将lambda表达式当作任意只包含一个抽象方法的接口类型，确保你的接口一定达到这个要求，
 *      你只需要给你的接口添加 @FunctionalInterface 注解，编译器如果发现你标注了这个注解的接口有多于一个抽象方法的时候会报错的。
 *
 *      需要注意接口如果只包含一个抽象方法的时候, 如果@FunctionalInterface如果没有指定，下面的代码也是对的。
 * 链接：<a href='https://www.jianshu.com/p/0bf8fe0f153b'>https://www.jianshu.com/p/0bf8fe0f153b</a>
 * Created by bamboo on 2019-10-27.
 */
@FunctionalInterface
public interface Converter<F, T> {

    T convert(F from);

//    void test(); //此句放开，怎上面函数式注解@FunctionInterface报受检异常，
}
