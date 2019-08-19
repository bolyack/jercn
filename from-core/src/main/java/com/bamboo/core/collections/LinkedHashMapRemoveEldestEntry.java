package com.bamboo.core.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  最近最少使用LRU原理
 * https://www.yiibai.com/java/util/linkedhashmap_removeeldestentry.html
 * https://www.cnblogs.com/wang-meng/p/7583491.html  Java中常见数据结构Map之LinkedHashMap
 */
public class LinkedHashMapRemoveEldestEntry {

    private static final int MAX_ENTRIES = 5;

    public static void main(String[] args) {
        LinkedHashMap lhm = new LinkedHashMap(MAX_ENTRIES + 1, 0.75f, false) {

            // 如果想删除eldest元素，并返回true,就应该覆盖这个方法。如果旧的条目从映射中删除此方法返回true;
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_ENTRIES;
            }
        };

        lhm.put(0, "H"); // 此时添加第一个元素，因映射表总容量，则最旧的条目是 0 -> H
        lhm.put(1, "E"); // 此时添加第二个元素，因映射表总容量，则最旧的条目是 0 -> H, 1-> E
        lhm.put(2, "L"); // 此时添加第三个元素，因映射表总容量，则最旧的条目是 0 -> H, 1-> E, 2 -> L
        lhm.put(3, "L"); // 此时添加第四个元素，因映射表总容量，则最旧的条目是 0 -> H, 1-> E, 2 -> L, 3 -> L
        lhm.put(4, "O"); // 此时添加第五个元素，因映射表总容量，则最旧的条目是 0 -> H, 1-> E, 2 -> L, 3 -> L, 4 -> O

        System.out.println("" + lhm); // 打印映射表里的5个元素

        lhm.put(5, "W"); // 此时添加第6个元素，因映射表总容量为5，则最旧的条目是 0 -> H 需要被移除, 则映射表条目   1-> E, 2 -> L, 3 -> L, 4 -> O, 5 -> W
        System.out.println("" + lhm);
        lhm.put(6, "Y"); // 此时添加第7个元素，因映射表总容量为5，则最旧的条目是 1 -> E 需要被移除, 则映射表条目   2 -> L, 3 -> L, 4 -> O, 5 -> W, 6 -> Y
        System.out.println("" + lhm);

    }

}
