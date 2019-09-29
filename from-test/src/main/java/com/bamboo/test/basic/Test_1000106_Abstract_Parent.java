package com.bamboo.test.basic;

//-----抽象类不可以继承接口------
//public abstract class Test_1000106_Abstract_Parent extends Test_1000106_Interface_Parent {//报错
//-----抽象类可以实现接口。-------
//public abstract class Test_1000106_Abstract_Parent implements Test_1000106_Interface_Parent {//正常

//-----抽象类可以继承实体类，就是因为抽象类的可以继承性和有方法。------
//-----抽象类是否可继承实体类，但前提是实体类必须有明确的构造函数。若把Test_1000106_Obj_Entity中的构造方法私有化，则报错------
public abstract class Test_1000106_Abstract_Parent extends Test_1000106_Obj_Entity {

}
