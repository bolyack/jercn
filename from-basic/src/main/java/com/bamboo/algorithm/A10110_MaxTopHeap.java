package com.bamboo.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * 延伸：https://www.jianshu.com/p/f1fd9b82cb72
 */
public class A10110_MaxTopHeap {

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
                maxHeap.offer(arr[i]);
            } else if (maxHeap.peek() > arr[i]) {
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
