package com.bamboo.test.basic.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test_Collection_100010_UnModified {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        ls.add("Hello");
        ls.add("Test");
        Collection<String> finalList = Collections.unmodifiableCollection(ls);
        finalList.add("Tail");//Exception in thread "main" java.lang.UnsupportedOperationException
        System.out.println(finalList);
        for (String st : finalList) {
            System.out.println(st);
        }
    }

}
