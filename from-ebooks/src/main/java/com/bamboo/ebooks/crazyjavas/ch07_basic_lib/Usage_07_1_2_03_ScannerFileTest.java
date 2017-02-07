package com.bamboo.ebooks.crazyjavas.ch07_basic_lib;

import java.io.File;
import java.util.Scanner;

/**
 * Created by bamboo on 2017/2/6.
 * 使用Scanner获取键盘输入-01-获取文件内容作为输入项
 */
public class Usage_07_1_2_03_ScannerFileTest {

    /**
     * Scanner 提供了两个简单的方法来逐行读取
     *    boolean hasNextLine(): 返回输入源中是否还有下一行。
     *    String nextLine()    : 返回输入源中下一行的字符串。
     *
     */

    public static void main(String[] args) throws Exception {
        //将一个File对象作为Scanner的构造器参数，Scanner读取文件内容
        Scanner scanner = new Scanner(new File("C:\\Users\\bamboo\\Desktop\\sss.sql"));

        System.out.println(" C:\\Users\\bamboo\\Desktop\\sss.sql文件内容如下：");
        //判断是否还有下一行
        while(scanner.hasNextLine()) {
            //输出文件中的下一行
            System.out.println("   " + scanner.nextLine());
        }
    }

}
