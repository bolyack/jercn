package com.bamboo.test.basic;

public class Test_1000104_Final_Static_Diff {

    public static void main(String[] args) {
        Test_1000104_Final_Static_Diff_Obj df1 = new Test_1000104_Final_Static_Diff_Obj();

        Test_1000104_Final_Static_Diff_Obj df2 = new Test_1000104_Final_Static_Diff_Obj();

        System.out.println(df1.i);
        System.out.println(df2.i);

        System.out.println("\n\n");

        System.out.println(df1.j);
        System.out.println(df2.j);

    }

}

//---static强调只有一份，final强调不变。----
class Test_1000104_Final_Static_Diff_Obj {
    public final double i = Math.random(); //--强调不能修改，初始化的时候，与实例有关
    //---static 是在内存中分配一块区域，供整个类通用，所有的类的对象都享有共同的值---
    public static double j = Math.random();//---在类加载初始化的时候，已经赋值---与实例无关
}