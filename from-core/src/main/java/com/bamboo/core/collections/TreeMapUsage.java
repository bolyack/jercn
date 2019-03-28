package com.bamboo.core.collections;

import java.util.*;

public class TreeMapUsage {

    public static void testSortByNumStr() {
        String str = "001,002,003,004,005,006,007,008,009,0010,0011,0012,0013,001001,001002,001201";
        List<String> list = new ArrayList<String>(Arrays.asList(str.split(",")));
        //treeMap is a sort collection, and it auto sort by key....
        TreeMap<String, String> tree = new TreeMap<String, String>();
        for (String ls : list) {
            tree.put(ls, ls);
        }

        for (Map.Entry<String, String> map : tree.entrySet()) {
            System.out.println(map.getKey());
        }
    }

    public static void main(String[] args) {
        testSortByNumStr();
    }

}
