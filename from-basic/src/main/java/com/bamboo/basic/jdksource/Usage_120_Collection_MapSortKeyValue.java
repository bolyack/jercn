package com.bamboo.basic.jdksource;

import com.bamboo.utils.collections.MapSortUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过key或者value值对map排序
 * @see  http://mp.weixin.qq.com/s?__biz=MzAxMjY5NDU2Ng==&mid=2651851137&idx=1&sn=d064dd00679891b286398998646ec442&chksm=80494cc8b73ec5dee4215e4802402d720d76f2a1266d190b25e96a5bb40763a3b0a5145bd434&mpshare=1&scene=22&srcid=0330lgsNV94koXopI1xMzdKu#rd
 * Created by admin on 2017/3/31.
 */
public class Usage_120_Collection_MapSortKeyValue {

    public static void main(String[] args) {
        Map map = new HashMap<String, String>();
        //*value Class should implements the Comparable interface
        //*String implements Comparable by default.
        map.put("Z", "3");
        map.put("D", "4");
        map.put("A", "1");
        map.put("02", "8");
        map.put("B", "2");
        map.put("01", "7");
        map.put("F", "6");
        map.put("E", "5");
        System.out.println("Unsorted Map:" + map);

        Map sortKeyMap = MapSortUtil.sortByKey(map);
        System.out.println("Sorted Map By Keys: " + sortKeyMap);

        /**
         * 为了解决通过value来排序，我们需要找一个办法
         *      TreeMap有一个构造器接受Comparator，它能够通过key来对map排序.
         *      方法就是让comparator能够访问到values代替key
         *      因此，我们通过map的方法 usingmap.get(key)得到values值，然后传入comparator
         *      注意这里value必须实现Comparable类型，values对象必须实现Comparable接口，这里例子里面的String属于是Comparable类型
         */
        Map sortValueMap = MapSortUtil.sortByValue(map);
        System.out.println("Sorted Map By Values:" + sortValueMap);

    }

}
