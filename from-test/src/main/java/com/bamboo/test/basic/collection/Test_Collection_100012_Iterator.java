package com.bamboo.test.basic.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * https://www.jianshu.com/p/999d045f8b68
 */
public class Test_Collection_100012_Iterator {

    public static void main(String[] args) throws Exception {

        ArrayList arrayList = new ArrayList(2);
        arrayList.add("aaaa");
        arrayList.add(234);
        arrayList.add(23.4);
        arrayList.add(2l);


        /*List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");

        for (Iterator it = list.iterator(); it.hasNext();) {
            String val = it.next().toString();
            System.out.println(val);
            if ("c".equals(val)) {
                it.remove();
            }
        }

        System.out.println("\n\n");
        Iterator listIterator = list.iterator();
        while(listIterator.hasNext()) {
            System.out.println(listIterator.next().toString());
        }*/

    }

}
