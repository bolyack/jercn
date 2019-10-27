package com.bamboo.basic.jdk8.feature_02_lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 测试jdk8新特性--Lambda表达式
 * Created by bamboo on 2019-10-27.
 */
public class TestLambda {

    static List<String> list = new ArrayList<>();

    static {
        list.add("peter");
        list.add("anna");
        list.add("mike");
        list.add("xenia");
    }

    /***
     * 传统的方式排序集合
     */
    public static void oldCollectionSort() {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list);
    }


    public static void main(String[] args) {
//        System.out.println("====传统排序集合====");
//        oldCollectionSort();


//        System.out.println("====jdk8使用Lambda表达式排序集合(一般方式)====");
//        Collections.sort(list, (String a, String b) -> {
//            return a.compareTo(b);
//        });
//        System.out.println(list);


        //对于上面函数体只有一行代码的，你可以去掉大括号{}以及return关键字， 如下：
//        System.out.println("====jdk8使用Lambda表达式排序集合(简化方式)====");
//        Collections.sort(list, (String a, String b) -> a.compareTo(b));
//        System.out.println(list);


        //上面还可以写得更短点，Java编译器可以自动推导出参数类型，所以你可以不用再写一次类型，如下：
        System.out.println("====jdk8使用Lambda表达式排序集合(进一步精简方式)====");
        Collections.sort(list, (a, b) -> a.compareTo(b));
        System.out.println(list);
    }

}
