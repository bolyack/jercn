package com.bamboo.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * topK：输入n个整数，找出其中最小的K个数。
 * @see https://www.jianshu.com/p/257820688bf1
 * 延伸：https://www.jianshu.com/p/f1fd9b82cb72
 */
public class A10110_MaxTopHeapPriorityQueue {

    /**
     * 使用大顶堆保存，比堆中的数小就进堆。
     * @param arr
     * @param k
     * @return
     */
    public static ArrayList<Integer> getLeastNumbers(int[] arr, int k) {
        ArrayList<Integer> rs = new ArrayList<>();
        int arrLength = arr.length;
        if (k > arrLength || k == 0) {
            return rs;
        }
        //最大堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < arrLength; i++) {
            if (maxHeap.size() != k) {
                // offer()方法用于将特定元素插入优先级队列。如果值成功插入队列，则该方法返回True。
                maxHeap.offer(arr[i]);
            } else if (maxHeap.peek() > arr[i]) {//peek()在方法调用返回此队列的头部，或null(如果此队列为空)。但它不会将其删除。
                // poll()方法用于检索或获取和删除队列的第一个元素或队列头部的元素。该方法返回队列头部的元素，如果队列为空，则返回NULL。
                Integer tmp = maxHeap.poll();
                tmp = null;
                maxHeap.offer(arr[i]);
            }
        }
        for (Integer a : maxHeap) {
            rs.add(a);
        }
        return rs;
    }

    public static void main(String[] args) {
        int[] num = {4,5,9,1,6,2,7,3,8};
        List<Integer> result = getLeastNumbers(num, 4);
        result.stream().forEach(System.out::println);
    }

}
