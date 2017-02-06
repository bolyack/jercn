package com.bamboo.ebooks.crazyjavas.chp07_basic_lib;

import java.util.Scanner;

/**
 * Created by bamboo on 2017/2/6.
 * 使用Scanner获取键盘输入-01-获取Long基本类型的输入项
 */
public class Usage_07_1_2_02_ScannerLongTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //判断是否还有下一个long型整数， 这就要求输入项必须是整数，否则程序就会退出
        while(scanner.hasNextLong()) {
            System.out.println("  键盘输入的long型整数内容是:" + scanner.nextLong());
        }
    }

}
