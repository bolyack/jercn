package com.bamboo.test.basic;

public class Test_1000104_Final_Variable {

    /** 基本类型用final修饰后，就无setXXX()方法, 表示 */
    private final int age = 27;
    private final static double high = 1.73;

    private final Test_1000104_Final_Variable_Obj vbj = new Test_1000104_Final_Variable_Obj("zhangsan");

    public static void main(String[] args) {
//        high = 2.6;   //报错，基本类型---不能修改
        Test_1000104_Final_Variable fv = new Test_1000104_Final_Variable();
//        fv.age = 34;  //报错，基本类型---不能修改

//        fv.vbj = new Test_1000104_Final_Variable_Obj("皇华");//报错，引用类型的变量---则在对其初始化之后便不能再让其指向另一个对象
        fv.vbj.setName("哇嘎");  //---虽然不能再指向其他对象，但是它指向的对象的内容是可变的。--
        System.out.println(fv.vbj.getName());

    }


}

class Test_1000104_Final_Variable_Obj {

    private String name;

    public Test_1000104_Final_Variable_Obj(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
