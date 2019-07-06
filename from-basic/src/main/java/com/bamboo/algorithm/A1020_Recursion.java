package com.bamboo.algorithm;

/**
 * 递归
 * url: https://www.cnblogs.com/kangkaii/p/8419089.html
 * Created by bamboo on 2019-07-06.
 */
public class A1020_Recursion {

    public static void main(String[] args) {
        System.out.println(factorial(10));

        for (int i = 1; i <= 9 ; i++) {
            System.out.println(i + ",," + fibonacci(i));
        }

//        printNineMutlNineByEach(9);
        printNineMutlNineByRecursion(9);
    }

    /**
     * 使用递归打印阶乘
     * //10! = 10 * 9 * 8 * 。。。。1；
     * @param n
     * @return
     */
    public static long factorial(int n) {
        long result = 0;
        if (n <= 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            result = n * factorial(n - 1);
        }
        return result;
    }


    /***
     * 使用递归打印斐波那契数列
     * //第一和第二项是1，后面每一项是前二项之和，即1，1，2，3，5，8，13,...。
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 2) + fibonacci(n - 1);
        }
    }


    /**
     * 9 * 9乘法口诀表 //for 循环实现
     * @param n
     */
    public static void printNineMutlNineByEach(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j ++) {
                System.out.print(j + " * " + i + " = " + i * j + "   ");
            }
            System.out.println();
        }
    }

    /***
     * 9 * 9乘法口诀表 //递归 实现
     * @param n
     */
    public static void printNineMutlNineByRecursion(int n) {
        if (n == 1) {
            System.out.println("1 * 1 = 1      ");
        } else {
            printNineMutlNineByRecursion(n - 1);
            for (int j = 1; j <= n; j++) {
                System.out.print(n + " * " + j + " = " + n * j + "      ");
            }
            System.out.println();
        }
    }


}
