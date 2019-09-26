package com.bamboo.test.basic;

public final class Test_1000100_Final_Class_Parent {

    //final类中的成员变量可以根据需要设为final，
    private int age;
    public String name;
    //---final修饰的基本类型，必须初始化----
//    private final String idCard; //---该行会报错---
    private final String idCard = "";

    //【但是要注意final类中的!!!所有成员方法!!!!都会被隐式地指定为final方法。】
    public String getInfo() {
        return name + "11";
    }

}
