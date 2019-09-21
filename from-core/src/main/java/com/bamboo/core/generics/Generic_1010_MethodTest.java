package com.bamboo.core.generics;

/**
 * 01_泛型方法_用例
 *      演示了如何使用泛型方法打印不同字符串的元素
 * @link https://www.jianshu.com/p/e9e5bd7c46f9
 * @link https://juejin.im/post/5b614848e51d45355d51f792#heading-14
 */
public class Generic_1010_MethodTest {

    /**
     * 定义泛型方法
     * @param arrays
     * @param <E>
     */
    public static <E> void printArray(E[] arrays) {
        for (E e : arrays) {
            System.out.printf("%s, ", e);
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Integer[] integerArr = {1, 3, 4, 2, 7, 5, 6, 9};
        Double[] doubleArr = {1.2, 4.2, 5.6, 7.4, 9.2, 3.8};
        Character[] charArr = {'A', 'C', 'D', 'F', 'B', 'E', 'N'};
        System.out.println("整型数组元素为：");
        printArray(integerArr);

        System.out.println("Double数组元素为：");
        printArray(doubleArr);

        System.out.println("字符数组元素为：");
        printArray(charArr);
    }

}
