package com.bamboo.basic;

/**
 * Created by admin on 2017/2/23.
 * Usage Operator Introduce
 */
public class Usage_Operator {

    public static void main(String[] args) {
        andRightMove();
    }

    /**
     * see jdk HashMap source in the 377 line( the method tableSizeFor()  )
     */
    public static void andRightMove() {
        int n = 6;
        printDecimalToBinary(n);  //  0000 0110
        // n >>> 2   binary num       0000 0001    equals 1
        n |= n >>> 2;             //  a |= b;  euqals  a = a + b;  so n values = 7
        System.out.println(n);

        System.out.println();

        int m = 9;
        printDecimalToBinary(m);  //  0000 1001
        // n >>> 2   binary num       0000 0010    equals 2
        m |= m >>> 2;             //  a |= b;  euqals  a = a + b;  so n values = 11
        System.out.println(m);
    }

    /**
     * turn decimal num to binary num and print it as string
     */
    public static void printDecimalToBinary(int num) {
        System.out.println(Integer.toBinaryString(num));
    }

}
