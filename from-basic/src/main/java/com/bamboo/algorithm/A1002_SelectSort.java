package com.bamboo.algorithm;

/**
 * 选择排序（Selection Sort）
 * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
 *
  * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * url: https://www.cnblogs.com/guoyaohua/p/8600214.html
 * Created by bamboo on 2019-07-06.
 */
public class A1002_SelectSort {

    public static void main(String[] args) {
        int[] arrs = {1, 4, 6, 2, 5, 7, 3, 9, 8, 0};
        int[] newArray = sortArray(arrs);
        printArray(newArray);
    }

    public static int[] sortArray(int[] array) {
        if (array.length == 0) return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex  = i;
            for (int j = i; j < array.length; j ++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static void printArray(int[] array) {
        if (array.length == 0) return;
        for (int i : array) {
            System.out.print(i + ", ");
        }
    }

}
