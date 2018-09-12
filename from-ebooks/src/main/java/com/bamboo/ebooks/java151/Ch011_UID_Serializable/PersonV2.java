package com.bamboo.ebooks.java151.Ch011_UID_Serializable;

import java.io.Serializable;

public class PersonV2 implements Serializable {

    private static final long serialVersionUID = -9065659304401531206L;

    private String name;
//    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}
