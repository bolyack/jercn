package com.bamboo.ebooks.java151;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 建议87-减少HashMap中元素的数量
 * 【注意】 尽量让HashMap中的元素少量并简单
 */
public class Ch078_HashMap {

    /**
     * OutOfMemory错误: Java heap space
     * 使用HashMap缓冲池操作数据，大批量的增删查改操作就可能会让内存溢出
     */
    private static void badHashMapShow() {
        Map<String, String> map = new HashMap<String, String>();
        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread(){
            public void run() {
                StringBuffer buffer = new StringBuffer();
                long heapMaxSize = rt.maxMemory() >> 20;
                buffer.append(" 最大可用内存：" + heapMaxSize + "M\n");
                long total = rt.totalMemory() >> 20;
                buffer.append(" 堆内存大小：" + total + "M\n");
                long free = rt.freeMemory() >> 20;
                buffer.append(" 空闲内存：" + free + "M");
                System.out.println(buffer);
            }
        });

        for (int i = 0; i < 10000000; i++) { //8G内存 10000000 溢出
            map.put("key-" + i, "value-" + i);
        }
    }

    /**
     * 同样量级的ArrayLiist 运行正常
     */
    private static void compareListShow() {
        List<String> list = new ArrayList<String>();
        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread(){
            public void run() {
                StringBuffer buffer = new StringBuffer();
                long total = rt.totalMemory() >> 20;
                buffer.append(" 堆内存大小：" + total + "M\n");
                long heapMaxSize = rt.maxMemory() >> 20;
                buffer.append(" 最大可用内存：" + heapMaxSize + "M\n");
                long free = rt.freeMemory() >> 20;
                buffer.append(" 空闲内存：" + free + "M");
                System.out.println(buffer);
            }
        });

        for (int i = 0; i < 10000000; i++) { //8G内存 10000000 未溢出
            list.add("key-" + i);
            list.add("value-" + i);
        }
    }


    /**
     * 综合来说，HashMap比ArrayList多了一层Entry的底层对象封装，多占用了内存，并且HashMap的扩容策略是2倍长度的递增，
     * 同时还会依据阀值判断规则进行判断，因此相对于ArrayList来说，它就会先出现内存溢出。
     * @param args
     */
    public static void main(String[] args) {
//        badHashMapShow();
//        compareListShow();
    }

}
