package com.bamboo.basic.jdk8.feature_22_action_usage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
//        transactions.stream().map(t -> t.getTrade().getCity()).distinct().forEach(c -> System.out.println(c));
        transactions.stream().map(t -> t.getTrade().getCity()).distinct().collect(Collectors.toList()).forEach(c -> System.out.println(c));
    }

    public static void main(String[] args) {
//        doTask1();
        doTask2();
    }



}
