package com.bamboo.basic.jdk8.feature_08_visit_interface_default.stream;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: jercn
 * @description: 使用Java8 Stream API对Map类型按照键或值进行排序
 * @author: bamboo
 * @create: 2020-05-25
 * @link https://baijiahao.baidu.com/s?id=1648174321869332085&wfr=spider&for=pc
 **/
public class StreamMapUsageTest {

    /**
     * HashMap的merge()函数
     * @param codes
     */
    public static void mergeUsage(Map<String, Integer> codes) {
        System.out.println("插入重复Key之前的Map集合对象:");
        codes.entrySet().stream().forEach(System.out::println);
        System.out.println("=================================");
        /**
         * 参数一：向map里面put的键
         * 参数二：向map里面put的值
         * 参数三：如果键发生重复，如何处理值。可以是一个函数，也可以写成lambda表达式。
         * 其中lambda表达式很简单：表示匿名函数，箭头左侧是参数，箭头右侧是函数体。函数的参数类型和返回值，由代码上下文来确定。
         */
        codes.merge("China", 100, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("插入重复Key之后的Map集合对象:");
        codes.entrySet().stream().forEach(System.out::println);
    }

    /**
     * 按照Map的键进行排序
     * @param coeds
     */
    public static void sortedByKey(Map<String, Integer> coeds) {
        //按照Map的======键======进行排序   使用entrySet().stream() 将Map类型转换为Stream流类型
        Map<String, Integer> sortedMap = coeds.entrySet().stream()
                //正向排序
//                .sorted(Map.Entry.comparingByKey())
                //按照键进行逆向排序
                .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                // 下面是一个merge规则的lambda表达式, 由于本例中没有重复的key，所以新值旧值随便返回一个即可。
                                (oldVal, newVal) -> oldVal,
                                // 最终将其返回为LinkedHashMap（可以保留排序顺序）
                                LinkedHashMap::new
                        )
                );

        //将排序后的Map打印
        sortedMap.entrySet().forEach(System.out::println);
    }

    /**
     * 按照Map的值进行排序
     * @param codes
     */
    public static void sortedByValue(Map<String, Integer> codes) {
        Map<String, Integer> sortedMap = codes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        // 最终将其返回为LinkedHashMap（可以保留排序顺序）
                        LinkedHashMap::new
                ));
        sortedMap.entrySet().forEach(System.out::println);
    }

    public static void main(String[] args) {
        //创建一个Map, 并填入数据
        Map<String, Integer> coeds = new HashMap<>();
        coeds.put("United States", 1);
        coeds.put("Germany", 49);
        coeds.put("France", 33);
        coeds.put("China", 86);
        coeds.put("Pakistan", 92);


        mergeUsage(coeds);

        //按照Map的======键======进行排序
//        sortedByKey(coeds);

        //按照Map的======值======进行排序
//        sortedByValue(coeds);
    }

}
