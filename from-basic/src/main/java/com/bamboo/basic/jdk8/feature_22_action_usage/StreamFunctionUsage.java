package com.bamboo.basic.jdk8.feature_22_action_usage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Deacription Stream函数
 * @Author bamboo
 * @Date 2020/1/15 16:34
 * @Version 1.0
 * @link https://www.jianshu.com/p/0a1cb55de384
 **/
public class StreamFunctionUsage {

    static List<StreamFunctionTransaction> transactions = new ArrayList<>(10);

    static {
        StreamFunctionTrade raoul = new StreamFunctionTrade("Raoul", "Cambridge");
        StreamFunctionTrade mario = new StreamFunctionTrade("Mario","Milan");
        StreamFunctionTrade alan = new StreamFunctionTrade("Alan","Cambridge");
        StreamFunctionTrade brian = new StreamFunctionTrade("Brian","Cambridge");

        transactions.add(new StreamFunctionTransaction(brian, 2011, 300));
        transactions.add(new StreamFunctionTransaction(raoul, 2012, 1000));
        transactions.add(new StreamFunctionTransaction(raoul, 2011, 400));
        transactions.add(new StreamFunctionTransaction(mario, 2012, 710));
        transactions.add(new StreamFunctionTransaction(mario, 2012, 700));
        transactions.add(new StreamFunctionTransaction(alan, 2012, 950));
    }

    /**
     * // 测试用例1 - (1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。
     */
    private static void doTask1() {
        transactions.stream().filter(f -> f.getYear() == 2011)
                /** 升序 */
                .sorted(Comparator.comparing(StreamFunctionTransaction::getAmount))
                /** 降序 */
//                .sorted(Comparator.comparing(StreamFunctionTransaction::getAmount).reversed())
                .collect(Collectors.toList())
                .forEach(c -> System.out.println(c));
    }

    /**
     * // 测试用例2 - (2) 交易员都在哪些不同的城市工作过?
     */
    private static void doTask2() {
        /** transactions.stream().map(t -> t.getTrade().getCity()).distinct().forEach(c -> System.out.println(c)); */
        transactions.stream().map(t -> t.getTrade().getCity()).distinct().collect(Collectors.toList()).forEach(c -> System.out.println(c));
    }

    /**
     * // 测试用例3 - (3) 查找所有来自于剑桥的交易员，并按姓名排序。
     */
    private static void doTask3() {
        transactions.stream().map(StreamFunctionTransaction::getTrade)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
//                .sorted(Comparator.comparing(StreamFunctionTrade::getName))
                .sorted((StreamFunctionTrade t1, StreamFunctionTrade t2) -> {
                    return t1.getName().compareTo(t2.getName());
                })
                .collect(Collectors.toList())
                .forEach(c -> System.out.println(c));
    }

    /**
     * // 测试用例4 - (4) 返回所有交易员的姓名字符串，按字母顺序排序。
     */
    private static void doTask4() {
        transactions.stream().map(StreamFunctionTransaction::getTrade)
                .map(StreamFunctionTrade::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList())
                .forEach(c -> System.out.println(c));
    }

    /**
     * // 测试用例5 - (5) 有没有交易员是在米兰工作的?
     */
    private static void doTask5() {
        Boolean isMatch = transactions.stream().map(StreamFunctionTransaction::getTrade)
                .anyMatch(t -> "Milan".equals(t.getCity()));
        System.out.println(String.format("isMatch: %s", isMatch));
    }

    /**
     * 测试用例6 - (6) 打印生活在剑桥的交易员的所有交易额。
     */
    private static void doTask6() {
        transactions.stream().filter(t -> "Cambridge".equals(t.getTrade().getCity()))
                .forEach(c -> System.out.println(c));
    }

    /**
     * 测试用例7 - (7) 所有交易中，最高的交易额是多少?
     */
    private static void doTask7() {
        Optional<Integer> optional = transactions.stream().map(StreamFunctionTransaction::getAmount).reduce(Integer::max);
        if (optional.isPresent()) {
            System.out.println("maxTransaction: " + optional.get());
        }
    }

    /**
     * 测试用例7 - (8) 找到交易额最小的交易。
     */
    private static void doTask8() {
        Optional<Integer> optional = transactions.stream().map(StreamFunctionTransaction::getAmount).reduce(Integer::min);
        if (optional.isPresent()) {
            System.out.println("minTransaction: " + optional.get());
        }

        Optional<StreamFunctionTransaction> minTransaction = transactions.stream().min(Comparator.comparing(StreamFunctionTransaction::getAmount));
        if (minTransaction.isPresent()) {
            System.out.println("minTransaction: " +minTransaction.get());
        }

        Optional<StreamFunctionTransaction> minTransactionOther = transactions.stream().reduce((c1, c2) -> c1.getAmount() < c2.getAmount() ? c1 : c2);
        if (minTransactionOther.isPresent()) {
            System.out.println("minTransactionOther: " +minTransaction.get());
        }

    }

    public static void main(String[] args) {
//        doTask1();
//        doTask2();
//        doTask3();
//        doTask4();
//        doTask5();
//        doTask6();
//        doTask7();
        doTask8();
    }



}
