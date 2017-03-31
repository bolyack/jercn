package com.bamboo.utils.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * 对Map的操作的工具类
 * Created by admin on 2017/3/31.
 */
public class MapSortUtil {

    private static Logger logger = LoggerFactory.getLogger(MapSortUtil.class);

    /**
     * 按照map的Value值进行排序
     * @param unSortedMap
     * @return
     */
    public static Map sortByValue(Map unSortedMap) {
        /**
         * 定义一个sortedMap，按照Map的value值排序的规则, 源码如下：
         * public TreeMap(Comparator<? super K> comparator) {
             this.comparator = comparator;
           }
         */
        Map sortedMap = new TreeMap(new MapValueComparator(unSortedMap));
        //putAll(unSortedMap) 初始化数据，把unSortedMap放入到sortedMap中，自动按照上面定义的规则去排序
        sortedMap.putAll(unSortedMap);
        return sortedMap;
    }

    /**
     * 按照map的Key值进行排序
     * @param unSortedMap
     * @return
     */
    public static Map sortByKey(Map unSortedMap) {
        Map sortedMap = new TreeMap(unSortedMap);
        /**
         * 下面语句可省略， 上面初始化语句源码如下：
         *      public TreeMap(Map<? extends K, ? extends V> m) {
                    comparator = null;
                    putAll(m);
                }
         */
        sortedMap.putAll(unSortedMap);
        return sortedMap;
    }

}
