package com.bamboo.basic.jdk8.feature_08_function_program;

import java.util.ArrayList;

/**
 * @Deacription jdk8 Stream、ParallelStream使用
 * @Author bamboo
 * @Date 2019/12/30 20:08
 * @Version 1.0
 **/
public class StreamAndParallel {

    static ArrayList lists = new ArrayList(10);
    static {
        lists.add("abc");
        lists.add("");
        lists.add("bc");
        lists.add("efg");
        lists.add("abcd");
        lists.add("");
        lists.add("jkl");
    }

    /**  为集合创建串行流 */
    public static void createStream() {
        lists.stream().forEach(System.out::println);
    }

    /** 为集合创建并行流 */
    public static void createParallelStream() {
        lists.parallelStream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        createStream();
//        createParallelStream();
    }


}
