package com.bamboo.ebooks.crazyjavas.ch07_basic_lib;

import java.util.Scanner;

/**
 * Created by bamboo on 2017/2/6.
 * 使用Scanner获取键盘输入-01-获取用户的键盘输入
 */
public class Usage_07_1_2_01_ScannerKeyBoardTest {

    /**
     * 使用Scanner类可以很方便地获取用户的键盘输入，Scanner是一个基于正则表达式的文本扫描器，它可以从文件、输入流、字符串中解析出基本类型值和字符串值。
     * Scanner类提供了多个构造器，不同的构造器可以接收文件、输入流、字符串作为数据源，用于从文件、输入流、字符串中解析数据。
     *   Scanner主要提供了两个方法来扫描输入
     *      hasNextXxx(): 是否还有下一个输入项，其中Xxx可以是Int、Long等代表基本数据类型的字符串。如果只是判断是否包含下一个字符串，
     *                    则直接使用hasNext()。
     *      nextXxx()   : 获取下一个输入项。Xxx的含义与上面方法中的Xxx相同。
     * 在默认情况下， Scanner使用空白(包括空格、Tab空白、回车)作为多个输入项之间的分割符。
     *
     */


    public static void main(String[] args) {
        //System.in 代表标准输入——就是键盘输入
        Scanner scan = new Scanner(System.in);

        //增加下面一行将只把回车作为分割符
        scan.useDelimiter("\n");
        /**
         在默认情况下， Scanner使用空白(包括空格、Tab空白、回车)作为多个输入项之间的分割符。
         如果希望改变Scanner的分割符(不使用空白作为分隔符)，如程序需要每次读取一行，不管这一行中是否包含空格，Scanner都把它当成一个输入项，在这种情况下
         可以把Scanner的分隔符设置为回车符，不再使用默认的空白作为分隔符。
         a: 如果没有上面一行指定使用分割符。
         > hello Spring boot
             键盘输入的内容是:hello
             键盘输入的内容是:Spring
             键盘输入的内容是:boot

         b: 如果使用上面一行指定分割符，则如下
         > hello Spring boot
             键盘输入的内容是:hello Spring boot
         */


        //判断是否还有下一个输入项
        while (scan.hasNext()) {
            //输出输入项
            System.out.println(" 键盘输入的内容是:" + scan.next());
        }

    }

    /**
     * Scanner的读取操作可能被阻塞(当前执行顺序流暂停)来等待信息的输入。如果输入源煤业结束，Scanner又读不到更多输入项时(尤其在键盘输入时比较常见)，
     * Scanner的hasNext()和next()方法都有可能阻塞，hasNext()方法是否阻塞与【和其相关的next()方法是否阻塞】无关。
     */

}
