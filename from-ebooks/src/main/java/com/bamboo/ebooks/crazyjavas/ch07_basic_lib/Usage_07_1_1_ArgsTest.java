package com.bamboo.ebooks.crazyjavas.ch07_basic_lib;

/**
 * Created by bamboo on 2017/2/6.
 * 7.1.1 运行Java程序的参数
 */
public class Usage_07_1_1_ArgsTest {

    /**
     *通过下面的main方法，回忆Java程序的入口——main()方法的方法签名：
     *  public修饰符: Java类由JVM调用，为了让JVM可以自由调用这个main()方法，所以使用public修饰符把这个方法暴露出来。
     *  static修饰符：JVM调用这个主方法时，不会先创建该主类的对象(然后通过这个对象来调用该主方法)去调用主方法。JVM直接通过该类来调用主方法，
     *               因此使用static修复该主方法。
     *  void返回值：  因为主方法被JVM调用，该方法的返回值将返回给JVM，这没有任何意义，因此该main()方法没有返回值。
     *
     */
    public static void main(String[] args) {

        //输出args数组的长度
        System.out.println("参数个数：" + args.length);
        //遍历args数组的每个元素
        for (int i =0; i < args.length; i++) {
            System.out.println("  运行Java程序的第" + i + "个参数是: " + args[i]);
        }

        /**
         * 运行Java程序时在类名后紧跟一个或多个字符串(多个字符串之间以空格隔开)，JVM就会把这些字符串依次赋给args数组元素。
         * 如果某参数本身也包含空格，则应该将该参数用双引号("")括起来，否则JVM会把这个空格当成参数的分隔符。
         *    如：java Usage_07_1_1_ArgsTest Hello "Srping boot"
         */

    }

}
