package com.bamboo.algorithm;

/**
 * 冒泡排序（Bubble Sort）
 * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * url: https://www.cnblogs.com/guoyaohua/p/8600214.html
 * Created by bamboo on 2019-07-06.
 */
public class A1001_BubbleSort {

    public static void main(String[] args) {
        int[] arrs = {1, 4, 6, 2, 5, 7, 3, 9, 8, 0};
        int[] newArray = bubbleSort(arrs);
        printArray(newArray);
    }

    public static int[] bubbleSort(int[] array) {
        if (array.length == 0) return array;
        for (int i = 0; i< array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
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
