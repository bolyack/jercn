package com.bamboo.test.basic.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * https://www.cnblogs.com/lijia0511/p/4960033.html
 */
public class Test_Collection_100011_IteratorDiffListIterator {

    public static void main(String[] args) {
        List<String> originalList = new ArrayList<>();
        originalList.add("元素一");
        originalList.add("元素二");
        originalList.add("元素三");
        originalList.add("元素四");

        //将该链接表转化为ListIterator
        ListIterator<String> listIterator = originalList.listIterator();

        System.out.println("使用ListIterator：顺序输出迭代器中的元素");
        while(listIterator.hasNext()) {
            System.out.print("  " + listIterator.next().toString() + ", ");
        }

        System.out.println("\n\n使用ListIterator：验证add() \n");
        listIterator.add("元素add-1");
        listIterator.add("元素add-2");

        System.out.println("使用ListIterator：hasPrevious与previous实现逆序输出的元素");
        while(listIterator.hasPrevious()) {
            System.out.print("  " + listIterator.previous().toString() + ", ");
        }
        System.out.println("\n");

        System.out.println("使用ListIterator：定位当前的索引位置");
        while(listIterator.hasNext()) {
            String obj = listIterator.next().toString();
            System.out.println("  元素："+ obj + ", 前一个索引："  + listIterator.previousIndex() + ", 后一个索引：" + listIterator.nextIndex());
            if (obj.contains("add")) {
                listIterator.set(obj.replaceAll("add", "sub"));
            }
        }
        System.out.println("\n使用ListIterator：实现对象的修改，set()方法可以实现");
        while(listIterator.hasPrevious()) {
            System.out.print("  " + listIterator.previous().toString() + ", ");
        }
        System.out.println("\n");


        for (Iterator it = originalList.iterator(); it.hasNext(); ) {
            String obj = it.next().toString();
            System.out.print("  " + obj + ", ");
            if (obj.equals("元素一")) {
//            it.set //无修改方法
                it.remove();
            }
            System.out.println();
        }
        System.out.println("  " + originalList);

    }

}
