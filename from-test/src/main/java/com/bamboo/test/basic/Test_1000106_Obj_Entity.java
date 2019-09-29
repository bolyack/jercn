package com.bamboo.test.basic;

public class Test_1000106_Obj_Entity {

    private int age;

    //-----抽象类是否可继承实体类，但前提是实体类必须有明确的构造函数。-------
//    private Test_1000106_Obj_Entity(){}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

//----普通类是否可继承实体类，但前提是实体类必须有明确的构造函数。------
class Test_1000106_Obj_Entity_Test extends Test_1000106_Obj_Entity {

}
