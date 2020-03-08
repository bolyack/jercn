package com.bamboo.basic.jdk8.feature_02_lambda;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bamboo on 2020-03-08.
 * @link https://www.cnblogs.com/xichji/p/11570387.html
 */
public class LambdaUsageDefine {

    /**
     * 简单语句--传统写法
     */
    private static void simpleSentenceForTradition() {
        List<String> lists = Arrays.asList("a", "d", "b", "c");
        for (String str : lists) {
            System.out.println(str);
        }
    }

    /**
     * 简单语句--使用Lambda写法
     */
    private static void simpleSentenceForLambda() {
        List<String> lists = Arrays.asList("a", "d", "b", "c");
        lists.stream().forEach(c -> System.out.println(c));
    }

    /**
     * 复杂语句--使用Lambda写法
     *    如果语句块比较复杂，使用 {} 包起来
     */
    private static void complexSentenceForLambda() {
        List<String> lists = Arrays.asList("a", "d", "b", "c");
        lists.stream().forEach(c -> {
            String upperVar = "->".concat(StringUtils.upperCase(c));
            System.out.println(c + upperVar);
        });
    }


    /**
     * Lambda表达式本质：本质是匿名内部类的改装，所以它使用到的变量都会隐式的转换成final的。
     */
    private static void showLambdaEssence() {
        List<Integer> lists = Arrays.asList(1, 4, 6, 7, 2, 8, 3);
        int appendNum = 10; // 此处相当于  final int appendNum = 10
        lists.stream().forEach(c -> {
//            appendNum += 5; //此处Lambda编译异常
            int res = c + appendNum;
            System.out.println(res);
        });
    }

    /**
     * 排序--传统写法
     */
    private static void sortByTradition() {
        List<Integer> lists = Arrays.asList(1, 4, 6, 7, 2, 8, 3);
        lists.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(lists);
    }

    /**
     * 排序--使用Lambda方式
     *     Lambda的返回值和参数类型由编译器推理得出，不需要显示定义，如果只有一行代码可以不写return语句。
     */
    private static void sortByLambda() {
        List<Integer> lists = Arrays.asList(1, 4, 6, 7, 2, 8, 3);
        lists.sort((o1, o2) -> o1.compareTo(o2));
        System.out.println(lists);
    }

    /**
     * Lambda使用误区
     */
    private static void lambdaUsageMisunderstanding() {
        List<Integer> lists = Arrays.asList(1, 4, 6, 7, 2, 8, 3);
        lists.stream().sorted((o1, o2) -> o1.compareTo(o2));
        System.out.println(lists); // 发现未完成排序 X

        //--因为stream().sorted()--> For ordered streams, the sort is stable.  For unordered streams, no stability guarantees are made.
        //--返回的是流
        List<Integer> retLists = lists.stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());
        System.out.println(retLists); //  发现完成排序 OK
    }

    public static void main(String[] args) {
        simpleSentenceForTradition();
        simpleSentenceForLambda();

        complexSentenceForLambda();

        showLambdaEssence();

        sortByTradition();
        sortByLambda();

        lambdaUsageMisunderstanding();
    }



}
