package com.bamboo.test.basic;

/**
 * 接口可以继承接口，抽象类不可以继承接口，但可以实现接口。
 */
//public interface Test_1000107_Interface_Extends_Sub extends Test_1000106_Interface_Parent {
//-----一个接口可以继承多个接口. interface C extends A, B {}是可以的. -------
public interface Test_1000107_Interface_Extends_Sub extends Test_1000106_Interface_Parent,Test_1000106_Interface_Parent_Agagin {

}
