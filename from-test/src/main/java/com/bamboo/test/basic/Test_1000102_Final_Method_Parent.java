package com.bamboo.test.basic;

public class Test_1000102_Final_Method_Parent {

    private int age;
    public String name;

    public final String getInfo() {
        return name + "11";
    }

    public String getOther() {
        return "aaa";
    }

    /**
     * 注：一个类中的private方法会隐式地被指定为final方法。
     * @return
     */
    private int getHigh() {
        return 1;
    }

}
