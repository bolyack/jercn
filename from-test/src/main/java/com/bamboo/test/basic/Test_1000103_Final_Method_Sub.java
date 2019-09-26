package com.bamboo.test.basic;

public class Test_1000103_Final_Method_Sub extends Test_1000102_Final_Method_Parent {


    /**
     *   使用final修饰方法的原因有两个。
     *      第一个原因是把方法锁定，以防任何继承类修改它的含义；
     *      第二个原因是效率。在早期的Java实现版本中，会将final方法转为内嵌调用。
     *              但是如果方法过于庞大，可能看不到内嵌调用带来的任何性能提升。
     *              在最近的Java版本中，不需要使用final方法进行这些优化了。
     *
     * 　　因此，只有在想明确禁止该方法在子类中被覆盖的情况下才将方法设置为final。
     * @return
     */
//    public String getInfo() {
//        return name + "11";
//    }


    @Override
    public String getOther() {
        return super.getOther();
    }


}
