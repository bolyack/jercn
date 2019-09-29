package com.bamboo.test.basic.reflect;

public class ReflectObj {

    public int age = 18;
//    private String name  = "aaa"; //--为演示修改属性，此处都是设置相同类型---
    private int name  = 100;
    public static int high = 15;
    private static int weight = 90;
//    public static final String home = "shanghai"; //--为演示修改属性，此处都是设置相同类型---
    public static final int home = 999;

    private int getAgeMethod() {
        return age;
    }

    public int getNameMethod() {
        return name;
    }

    public static int getHomeStaticMethod() {
        return home;
    }

    public final String getFinalMethod() {
        return "cdd";
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(int name) {
        this.name = name;
    }

    public static int getHigh() {
        return high;
    }

    public static void setHigh(int high) {
        ReflectObj.high = high;
    }

    public static int getWeight() {
        return weight;
    }

    public static void setWeight(int weight) {
        ReflectObj.weight = weight;
    }

    public static int getHome() {
        return home;
    }
}
