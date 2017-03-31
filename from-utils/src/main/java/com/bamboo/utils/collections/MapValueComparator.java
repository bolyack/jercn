package com.bamboo.utils.collections;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by admin on 2017/3/31.
 */
public class MapValueComparator implements Comparator {

    private Map map;

    public MapValueComparator(Map map) {
        this.map = map;
    }

    public int compare(Object o1, Object o2) {

        Comparable value1 = (Comparable)map.get(o1);
        Comparable value2 = (Comparable)map.get(o2);

//        System.out.println(value1 + "-----" + value2);
        return value1.compareTo(value2);
    }
}
